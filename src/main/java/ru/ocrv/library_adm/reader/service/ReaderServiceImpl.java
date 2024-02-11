package ru.ocrv.library_adm.reader.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ocrv.library_adm.exception.exceptions.AccessDeniedException;
import ru.ocrv.library_adm.exception.exceptions.NotFoundException;
import ru.ocrv.library_adm.reader.QReader;
import ru.ocrv.library_adm.reader.Reader;
import ru.ocrv.library_adm.reader.ReaderMapper;
import ru.ocrv.library_adm.reader.ReaderStorage;
import ru.ocrv.library_adm.reader.dto.ReaderDtoRequest;
import ru.ocrv.library_adm.reader.dto.ReaderDtoResponse;
import ru.ocrv.library_adm.rent.RentStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderStorage readerStorage;
    private final RentStorage rentStorage;

    @Override
    public ReaderDtoResponse createReader(ReaderDtoRequest request) {
        Reader reader = readerStorage.save(ReaderMapper.toReader(request));
        log.info("Создан читатель с id: {}", reader.getId());

        return ReaderMapper.toReaderDtoResponse(reader);
    }

    @Override
    public void deleteReader(Long id) {
        isReaderExists(id);
        if (rentStorage.existsByReaderIdAndEndDate(id, null)) {
            throw new AccessDeniedException("У читателя есть не завершенные аренды");
        }
        readerStorage.deleteById(id);
        log.info("Удален читатель с id: {}", id);
    }

    @Override
    public ReaderDtoResponse updateReader(Long id, ReaderDtoRequest request) {
        Reader reader = findReader(id);
        Optional.ofNullable(reader.getPassportNumber()).ifPresent(reader::setPassportNumber);
        Optional.ofNullable(reader.getPassportSeries()).ifPresent(reader::setPassportSeries);
        Optional.ofNullable(reader.getFirstName()).ifPresent(reader::setFirstName);
        Optional.ofNullable(reader.getLastName()).ifPresent(reader::setLastName);
        Optional.ofNullable(reader.getPatronymic()).ifPresent(reader::setPatronymic);
        Optional.ofNullable(reader.getAddress()).ifPresent(reader::setAddress);
        Optional.ofNullable(reader.getEmail()).ifPresent(reader::setEmail);
        Optional.ofNullable(reader.getPhone()).ifPresent(reader::setPhone);

        Reader updatedReader = readerStorage.save(reader);
        log.info("Обновлен читатель с id: {}", updatedReader.getId());

        return ReaderMapper.toReaderDtoResponse(updatedReader);
    }

    @Override
    public ReaderDtoResponse getReader(Long id) {
        Reader reader = findReader(id);
        log.info("Прочитан читатель с id: {}", reader.getId());

        return ReaderMapper.toReaderDtoResponse(reader);
    }

    @Override
    public List<ReaderDtoResponse> searchReaders(String word, int from, int size) {
        if (StringUtils.isBlank(word)) {
            return new ArrayList<>();
        }
        List<BooleanExpression> conditions = new ArrayList<>();
        conditions.add(QReader.reader.passportNumber.containsIgnoreCase(word));
        conditions.add(QReader.reader.passportSeries.containsIgnoreCase(word));
        conditions.add(QReader.reader.firstName.containsIgnoreCase(word));
        conditions.add(QReader.reader.lastName.containsIgnoreCase(word));
        conditions.add(QReader.reader.patronymic.containsIgnoreCase(word));
        conditions.add(QReader.reader.address.containsIgnoreCase(word));
        conditions.add(QReader.reader.email.containsIgnoreCase(word));
        conditions.add(QReader.reader.phone.containsIgnoreCase(word));
        BooleanExpression finalCond = conditions.stream().reduce(BooleanExpression::or).get();

        Pageable pageable = PageRequest.of(from / size, size);
        Page<Reader> readers = readerStorage.findAll(finalCond, pageable);
        log.info("Поиск завершен по строке: {}", word);

        return readers.toList()
                .stream()
                .map(ReaderMapper::toReaderDtoResponse)
                .collect(Collectors.toList());
    }

    private void isReaderExists(Long id) {
        if (!readerStorage.existsReaderById(id)) {
            throw new NotFoundException("Читатель с id " + id + " не найден");
        }
    }

    private Reader findReader(Long id) {
        return readerStorage.findById(id)
                .orElseThrow(() -> new NotFoundException("Читатель с id " + id + " не найден"));
    }
}
