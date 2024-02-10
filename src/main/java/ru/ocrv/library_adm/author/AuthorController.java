package ru.ocrv.library_adm.author;

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
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDtoResponse createAuthor(@Valid @RequestBody AuthorDtoRequest request) {
        log.info("author - Создание нового автора");
        return authorService.createAuthor(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long id) {
        log.info("author - Удаление автора с id: {}", id);
        authorService.deleteAuthor(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse updateAuthor(@PathVariable Long id,
                                          @Valid @RequestBody AuthorDtoRequest request) {
        log.info("author - Обновление автора с id: {}", id);
        return authorService.updateAuthor(id, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse getAuthor(@PathVariable Long id) {
        log.info("author - Получение автора с id: {}", id);
        return authorService.getAuthor(id);
    }

    @GetMapping("/search/{word}")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDtoResponse> searchAuthors(@PathVariable String word,
                                                 @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                                 @RequestParam(defaultValue = "100") @Positive int size) {
        log.info("author - Поиск авторов по фразе: {}", word);
        return authorService.searchAuthors(word, from, size);
    }
}
