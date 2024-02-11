package ru.ocrv.library_adm.author;

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
import ru.ocrv.library_adm.author.dto.AuthorDtoRequest;
import ru.ocrv.library_adm.author.dto.AuthorDtoResponse;
import ru.ocrv.library_adm.author.srvice.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/author")
@AllArgsConstructor
@Slf4j
@Validated
@Tag(name = "Авторы", description = "Управление авторами произведений")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создание нового автора")
    public AuthorDtoResponse createAuthor(@Valid @RequestBody @Parameter AuthorDtoRequest request) {
        log.info("author - Создание нового автора");
        return authorService.createAuthor(request);
    }

    @Operation(summary = "Удаление автора")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable @Parameter(description = "ID автора") Long id) {
        log.info("author - Удаление автора с id: {}", id);
        authorService.deleteAuthor(id);
    }

    @Operation(summary = "Обновление автора")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse updateAuthor(@PathVariable @Parameter(description = "ID автора") Long id,
                                          @Valid @RequestBody AuthorDtoRequest request) {
        log.info("author - Обновление автора с id: {}", id);
        return authorService.updateAuthor(id, request);
    }

    @Operation(summary = "Чтение автора по ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse getAuthor(@PathVariable @Parameter(description = "ID автора") Long id) {
        log.info("author - Получение автора с id: {}", id);
        return authorService.getAuthor(id);
    }

    @Operation(summary = "Поиск автора по строке")
    @GetMapping("/search/{word}")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDtoResponse> searchAuthors(@PathVariable @Parameter(description = "Строка поиска") String word,
                                                 @RequestParam(defaultValue = "0")
                                                 @Parameter(description = "Пагинация с") @PositiveOrZero int from,
                                                 @RequestParam(defaultValue = "100")
                                                 @Parameter(description = "Пагинация размер") @Positive int size) {
        log.info("author - Поиск авторов по фразе: {}", word);
        return authorService.searchAuthors(word, from, size);
    }
}
