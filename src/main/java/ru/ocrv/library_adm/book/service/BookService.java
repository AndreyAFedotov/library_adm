package ru.ocrv.library_adm.book.service;

import ru.ocrv.library_adm.book.Book;
import ru.ocrv.library_adm.book.dto.BookDtoRequest;
import ru.ocrv.library_adm.book.dto.BookDtoResponse;

import java.util.List;

public interface BookService {

    BookDtoResponse createBook(BookDtoRequest request);

    void deleteBook(Long id);

    BookDtoResponse updateBook(Long id, BookDtoRequest request);

    BookDtoResponse getBook(Long id);

    List<BookDtoResponse> searchBooks(String word, int from, int size);

    Boolean isBookExists(Long id);

    Book findBook(Long id);

    Boolean isBooksByAuthorExists(Long authorId);

    List<BookDtoResponse> searchBooksByAuthor(Long authorId, int from, int size);

    Boolean isBookRented(Long id);
}
