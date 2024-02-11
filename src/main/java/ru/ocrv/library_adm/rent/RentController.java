package ru.ocrv.library_adm.rent;

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
import ru.ocrv.library_adm.rent.dto.RentDtoRequest;
import ru.ocrv.library_adm.rent.dto.RentDtoResponse;
import ru.ocrv.library_adm.rent.service.RentService;

import java.util.List;

@RestController
@RequestMapping("/rent")
@AllArgsConstructor
@Slf4j
@Validated
@Tag(name = "Аренда", description = "Управление арендой произведений")
public class RentController {

    private final RentService rentService;

    @Operation(summary = "Создание новой аренды")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentDtoResponse createRent(@Valid @RequestBody RentDtoRequest request) {
        log.info("rent - Добавление новой аренды");
        return rentService.createRent(request);
    }

    @Operation(summary = "Удаление аренды (только закрытой)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRent(@PathVariable @Parameter(description = "ID аренды") Long id) {
        log.info("rent - Удаление аренды с id: {}", id);
        rentService.deleteRent(id);
    }

    @Operation(summary = "Закрытие аренды")
    @PatchMapping("/close/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void closeRent(@PathVariable @Parameter(description = "ID аренды") Long id) {
        log.info("rent - Завершение аренды с id: {}", id);
        rentService.closeRent(id);
    }

    @Operation(summary = "Получение аренды по ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentDtoResponse getRent(@PathVariable @Parameter(description = "ID аренды") Long id) {
        log.info("rent - Получение аренды с id: {}", id);
        return rentService.getRent(id);
    }

    @Operation(summary = "Поиск аренд для читателя по его ID")
    @GetMapping("/search/{readerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<RentDtoResponse> searchRentsForReader(@PathVariable @Parameter(description = "ID читателя") Long readerId,
                                                      @RequestParam(defaultValue = "0")
                                                      @PositiveOrZero @Parameter(description = "Пагинация с") int from,
                                                      @RequestParam(defaultValue = "100")
                                                      @Positive @Parameter(description = "Пагинация размер") int size) {
        log.info("rent - Поиск аренд по читателю с id: {}", readerId);
        return rentService.getRentsForReader(readerId, from, size);
    }
}
