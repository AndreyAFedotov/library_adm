package ru.ocrv.library_adm.book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Книги", description = "Управление произведениями")
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Добавление произведения")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDtoResponse createBook(@Valid @RequestBody BookDtoRequest request) {
        log.info("book - Добавление новой книги");
        return bookService.createBook(request);
    }

    @Operation(summary = "Удаление произведения")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable @Parameter(description = "ID книги") Long id) {
        log.info("book - Удаление книги с id: {}", id);
        bookService.deleteBook(id);
    }

    @Operation(summary = "Обновление произведения")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDtoResponse updateBook(@PathVariable @Parameter(description = "ID книги") Long id,
                                      @Valid @RequestBody BookDtoRequest request) {
        log.info("book - Обновление книги с id: {}", id);
        return bookService.updateBook(id, request);
    }

    @Operation(summary = "Получение произведения по ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDtoResponse getBook(@PathVariable @Parameter(description = "ID книги") Long id) {
        log.info("book - Получение книги с id: {}", id);
        return bookService.getBook(id);
    }

    @Operation(summary = "Поиск произведения по строке")
    @GetMapping("/search/{word}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDtoResponse> searchBooks(@PathVariable @Parameter(description = "Строка поиска") String word,
                                             @RequestParam(defaultValue = "0")
                                             @PositiveOrZero @Parameter(description = "пагинация с") int from,
                                             @RequestParam(defaultValue = "100")
                                             @Positive @Parameter(description = "пагинация размер") int size) {
        log.info("book - Поиск книг по фразе: {}", word);
        return bookService.searchBooks(word, from, size);
    }

    @Operation(summary = "Поиск произведения по ID автора")
    @GetMapping("/search/author/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDtoResponse> searchBooksByAuthor(@PathVariable @Parameter(description = "ID автора") Long authorId,
                                                     @RequestParam(defaultValue = "0")
                                                     @PositiveOrZero @Parameter(description = "Пагинация с") int from,
                                                     @RequestParam(defaultValue = "100")
                                                     @Positive @Parameter(description = "Пагинация размер") int size) {
        log.info("book - Поиск книг по автору: {}", authorId);
        return bookService.searchBooksByAuthor(authorId, from, size);
    }
}
