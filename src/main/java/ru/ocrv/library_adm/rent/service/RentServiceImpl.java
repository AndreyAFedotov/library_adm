package ru.ocrv.library_adm.rent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ocrv.library_adm.book.Book;
import ru.ocrv.library_adm.book.BookStorage;
import ru.ocrv.library_adm.exception.exceptions.AccessDeniedException;
import ru.ocrv.library_adm.exception.exceptions.NotFoundException;
import ru.ocrv.library_adm.reader.Reader;
import ru.ocrv.library_adm.reader.ReaderStorage;
import ru.ocrv.library_adm.rent.Rent;
import ru.ocrv.library_adm.rent.RentMapper;
import ru.ocrv.library_adm.rent.RentStorage;
import ru.ocrv.library_adm.rent.dto.RentDtoRequest;
import ru.ocrv.library_adm.rent.dto.RentDtoResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentStorage rentStorage;
    private final BookStorage bookStorage;
    private final ReaderStorage readerStorage;

    @Override
    public RentDtoResponse createRent(RentDtoRequest request) {
        Book book = findBook(request.getBook());
        if (isBookRented(book.getId())) {
            throw new AccessDeniedException("Книга уже в аренде");
        }
        Reader reader = findReader(request.getReader());
        Rent rent = rentStorage.save(RentMapper.toRent(book, reader, LocalDateTime.now(), null));
        book.setRented(true);
        Book rentedBook = bookStorage.save(book);
        log.info("Создана аренда с id {} для книги с id {}", rent.getId(), rentedBook.getId());

        return RentMapper.toRentDtoResponse(rent);
    }

    @Override
    public void closeRent(Long id) {
        Rent rent = findRent(id);
        if (rent.getEndDate() != null) {
            throw new AccessDeniedException("Аренда с id " + id + " уже завершена");
        }
        Book book = findBook(rent.getBook().getId());
        rent.setEndDate(LocalDateTime.now());
        Rent closedRent = rentStorage.save(rent);
        book.setRented(false);
        Book updatedBook = bookStorage.save(book);
        log.info("Завершена аренда с id {} для книги с id {}", closedRent.getId(), updatedBook.getId());
    }

    @Override
    public void deleteRent(Long id) {
        Rent rent = findRent(id);
        if (rent.getEndDate() == null) {
            throw new AccessDeniedException("Аренда с id " + id + " открыта. Удалить невозможно");
        }
        rentStorage.deleteById(id);
        log.info("Аренда с id {} удалена", id);
    }

    @Override
    public List<RentDtoResponse> getRentsForReader(Long readerId, int from, int size) {
        isReaderExists(readerId);
        Pageable pageable = PageRequest.of(from / size, size);
        Page<Rent> rents = rentStorage.findRentsByReaderId(readerId, pageable);
        log.info("Поиск аренд для читателя с id {} завешен", readerId);

        return rents.toList()
                .stream()
                .map(RentMapper::toRentDtoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RentDtoResponse getRent(Long id) {
        Rent rent = findRent(id);
        log.info("Найдена аренда с id: {}", rent.getId());

        return RentMapper.toRentDtoResponse(rent);
    }

    private Rent findRent(Long id) {
        return rentStorage.findById(id)
                .orElseThrow(() -> new NotFoundException("Аренда с id " + id + " не найдена"));
    }

    private Book findBook(Long id) {
        return bookStorage.findById(id)
                .orElseThrow(() -> new NotFoundException("Книга с id " + id + " не найдена"));
    }

    private Boolean isBookRented(Long id) {
        return bookStorage.existsBookByIdAndRented(id, true);
    }

    private Reader findReader(Long id) {
        return readerStorage.findById(id)
                .orElseThrow(() -> new NotFoundException("Читатель с id " + id + " не найден"));
    }

    private void isReaderExists(Long id) {
        if (!readerStorage.existsReaderById(id)) {
            throw new NotFoundException("Читатель с id " + id + " не найден");
        }
    }
}
