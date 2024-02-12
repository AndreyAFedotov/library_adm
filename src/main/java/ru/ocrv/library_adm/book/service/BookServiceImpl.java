package ru.ocrv.library_adm.book.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ocrv.library_adm.author.Author;
import ru.ocrv.library_adm.author.AuthorStorage;
import ru.ocrv.library_adm.book.Book;
import ru.ocrv.library_adm.book.BookMapper;
import ru.ocrv.library_adm.book.BookStorage;
import ru.ocrv.library_adm.book.QBook;
import ru.ocrv.library_adm.book.dto.BookDtoRequest;
import ru.ocrv.library_adm.book.dto.BookDtoResponse;
import ru.ocrv.library_adm.exception.exceptions.AccessDeniedException;
import ru.ocrv.library_adm.exception.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookStorage bookStorage;
    private final AuthorStorage authorStorage;

    @Override
    public BookDtoResponse createBook(BookDtoRequest request) {
        Author author = findAuthor(request.getAuthor());
        Book book = bookStorage.save(BookMapper.toBook(author, request));
        log.info("Создана книга с id: {}", book.getId());

        return BookMapper.toBookDtoResponse(book);
    }

    @Override
    public void deleteBook(Long id) {
        isBookExists(id);
        isBookRented(id);
        bookStorage.deleteById(id);
        log.info("Удалена книга с id: {}", id);
    }

    @Override
    public BookDtoResponse updateBook(Long id, BookDtoRequest request) {
        Book book = findBook(id);
        isAuthorExists(request.getAuthor());
        Optional.ofNullable(findAuthor(request.getAuthor())).ifPresent(book::setAuthor);
        Optional.ofNullable(request.getTitle()).ifPresent(book::setTitle);
        Optional.ofNullable(request.getPublishing()).ifPresent(book::setPublishing);
        Optional.ofNullable(request.getPublishingDate()).ifPresent(book::setPublishingDate);

        Book updatedBook = bookStorage.save(book);
        log.info("Обновлена книга с id: {}", updatedBook.getId());

        return BookMapper.toBookDtoResponse(updatedBook);
    }

    @Override
    public BookDtoResponse getBook(Long id) {
        Book book = findBook(id);
        log.info("Прочитана книга с id: {}", book.getId());

        return BookMapper.toBookDtoResponse(book);
    }

    @Override
    public List<BookDtoResponse> searchBooks(String word, int from, int size) {
        if (StringUtils.isBlank(word)) {
            return new ArrayList<>();
        }
        List<BooleanExpression> conditions = new ArrayList<>();
        conditions.add(QBook.book.author.firstName.containsIgnoreCase(word));
        conditions.add(QBook.book.author.lastName.containsIgnoreCase(word));
        conditions.add(QBook.book.author.patronymic.containsIgnoreCase(word));
        conditions.add(QBook.book.title.containsIgnoreCase(word));
        conditions.add(QBook.book.publishing.containsIgnoreCase(word));
        BooleanExpression finalCond = conditions.stream().reduce(BooleanExpression::or).get();

        Pageable pageable = PageRequest.of(from / size, size);
        Page<Book> books = bookStorage.findAll(finalCond, pageable);
        log.info("Поиск завершен по строке: {}", word);

        return books.toList()
                .stream()
                .map(BookMapper::toBookDtoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDtoResponse> searchBooksByAuthor(Long authorId, int from, int size) {
        isAuthorExists(authorId);
        Pageable pageable = PageRequest.of(from / size, size);
        Page<Book> books = bookStorage.searchBooksByAuthorId(authorId, pageable);
        log.info("Поиск завершен для автора с id: {}", authorId);

        return books.toList()
                .stream()
                .map(BookMapper::toBookDtoResponse)
                .collect(Collectors.toList());
    }

    private void isBookExists(Long id) {
        if (!bookStorage.existsBookById(id)) {
            throw new NotFoundException("Книга с id " + id + " не найдена");
        }
    }

    private Book findBook(Long id) {
        return bookStorage.findById(id)
                .orElseThrow(() -> new NotFoundException("Книга с id " + id + " не найдена"));
    }

    private Author findAuthor(Long id) {
        return authorStorage.findById(id)
                .orElseThrow(() -> new NotFoundException("Автор с id " + id + " не найден"));
    }

    private void isAuthorExists(Long id) {
        if (!authorStorage.existsAuthorById(id)) {
            throw new NotFoundException("Автор с id " + id + " не найден");
        }
    }

    private void isBookRented(Long id) {
        if (bookStorage.existsBookByIdAndRented(id, true)) {
            throw new AccessDeniedException("Книга находится в аренде");
        }
    }

}
