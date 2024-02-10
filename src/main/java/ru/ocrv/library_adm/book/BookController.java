package ru.ocrv.library_adm.book;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ocrv.library_adm.book.dto.BookDtoRequest;
import ru.ocrv.library_adm.book.dto.BookDtoResponse;
import ru.ocrv.library_adm.book.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
@Slf4j
@Validated
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDtoResponse createBook(@Valid @RequestBody BookDtoRequest request) {
        log.info("book - Добавление новой книги");
        return bookService.createBook(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        log.info("book - Удаление книги с id: {}", id);
        bookService.deleteBook(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDtoResponse updateBook(@PathVariable Long id,
                                      @Valid @RequestBody BookDtoRequest request) {
        log.info("book - Обновление книги с id: {}", id);
        return bookService.updateBook(id, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDtoResponse getBook(@PathVariable Long id) {
        log.info("book - Получение книги с id: {}", id);
        return bookService.getBook(id);
    }

    @GetMapping("/search/{word}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDtoResponse> searchBooks(@PathVariable String word,
                                             @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                             @RequestParam(defaultValue = "100") @Positive int size) {
        log.info("book - Поиск книг по фразе: {}", word);
        return bookService.searchBooks(word, from, size);
    }

    @GetMapping("/search/author/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDtoResponse> searchBooksByAuthor(@PathVariable Long authorId,
                                                     @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                                     @RequestParam(defaultValue = "100") @Positive int size) {
        log.info("book - Поиск книг по автору: {}", authorId);
        return  bookService.searchBooksByAuthor(authorId, from, size);
    }
}
