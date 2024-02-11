package ru.ocrv.library_adm.reader;

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
import ru.ocrv.library_adm.reader.dto.ReaderDtoRequest;
import ru.ocrv.library_adm.reader.dto.ReaderDtoResponse;
import ru.ocrv.library_adm.reader.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/reader")
@AllArgsConstructor
@Slf4j
@Validated
@Tag(name = "Читатели", description = "Управление читателями")
public class ReaderController {

    private final ReaderService readerService;

    @Operation(summary = "Добавление нового читателя")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReaderDtoResponse createReader(@Valid @RequestBody ReaderDtoRequest request) {
        log.info("reader - Добавление нового читателя");
        return readerService.createReader(request);
    }

    @Operation(summary = "Удаление читателя")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReader(@PathVariable @Parameter(description = "ID читателя") Long id) {
        log.info("reader - Удаление читателя с id: {}", id);
        readerService.deleteReader(id);
    }

    @Operation(summary = "Обновление читателя")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReaderDtoResponse updateReader(@PathVariable @Parameter(description = "ID читателя") Long id,
                                          @Valid @RequestBody ReaderDtoRequest request) {
        log.info("reader - Обновление читателя с id: {}", id);
        return readerService.updateReader(id, request);
    }

    @Operation(summary = "Получение читателя по ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReaderDtoResponse getReader(@PathVariable @Parameter(description = "ID читателя") Long id) {
        log.info("reader - Получение читателя с id: {}", id);
        return readerService.getReader(id);
    }

    @Operation(summary = "Поиск читателя по строке")
    @GetMapping("/search/{word}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReaderDtoResponse> searchReaders(@PathVariable @Parameter(description = "Строка поиска") String word,
                                                 @RequestParam(defaultValue = "0")
                                                 @PositiveOrZero @Parameter(description = "Пагинация с") int from,
                                                 @RequestParam(defaultValue = "100")
                                                 @Positive @Parameter(description = "Пагинация размер") int size) {
        log.info("reader - Поиск читателя по фразе: {}", word);
        return readerService.searchReaders(word, from, size);
    }
}
