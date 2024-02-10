package ru.ocrv.library_adm.rent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ocrv.library_adm.book.Book;
import ru.ocrv.library_adm.book.service.BookService;
import ru.ocrv.library_adm.exception.exceptions.AccessDeniedException;
import ru.ocrv.library_adm.reader.Reader;
import ru.ocrv.library_adm.reader.service.ReaderService;
import ru.ocrv.library_adm.rent.RentMapper;
import ru.ocrv.library_adm.rent.Rent;
import ru.ocrv.library_adm.rent.RentStorage;
import ru.ocrv.library_adm.rent.dto.RentDtoRequest;
import ru.ocrv.library_adm.rent.dto.RentDtoResponse;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentStorage rentStorage;
    private final BookService bookService;
    private final ReaderService readerService;

    @Override
    public RentDtoResponse createRent(RentDtoRequest request) {
        Book book = bookService.findBook(request.getBook());
        if (bookService.isBookRented(book.getId())) {
            throw new AccessDeniedException("Книга уже в аренде");
        }
        Reader reader = readerService.findReader(request.getReader());
        Rent rent = rentStorage.save(RentMapper.toRent(book, reader, LocalDateTime.now(), null));
        log.info("Создана аренда с id: {}", rent.getId());

        return RentMapper.toRentDtoResponse(rent);
    }

    @Override
    public void closeRent(Long id) {

    }

    @Override
    public void deleteRent(Long id) {

    }

    @Override
    public RentDtoResponse updateRent(Long id, RentDtoRequest request) {
        return null;
    }

    @Override
    public List<RentDtoResponse> getRents(int from, int size) {
        return null;
    }

    @Override
    public List<RentDtoResponse> getRentsForReader(Long readerId, int from, int size) {
        return null;
    }

    @Override
    public RentDtoResponse getRent(Long id) {
        return null;
    }
}
