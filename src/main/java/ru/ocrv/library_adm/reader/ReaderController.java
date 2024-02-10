package ru.ocrv.library_adm.reader;

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
public class ReaderController {

    private final ReaderService readerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReaderDtoResponse createReader(@Valid @RequestBody ReaderDtoRequest request) {
        log.info("reader - Добавление нового читателя");
        return readerService.createReader(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReader(@PathVariable Long id) {
        log.info("reader - Удаление читателя с id: {}", id);
        readerService.deleteReader(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReaderDtoResponse updateReader(@PathVariable Long id,
                                        @Valid @RequestBody ReaderDtoRequest request) {
        log.info("reader - Обновление читателя с id: {}", id);
        return readerService.updateReader(id, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReaderDtoResponse getReader(@PathVariable Long id) {
        log.info("reader - Получение читателя с id: {}", id);
        return readerService.getReader(id);
    }

    @GetMapping("/search/{word}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReaderDtoResponse> searchReaders(@PathVariable String word,
                                                 @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                                 @RequestParam(defaultValue = "100") @Positive int size) {
        log.info("reader - Поиск читателя по фразе: {}", word);
        return readerService.searchReaders(word, from, size);
    }
}
