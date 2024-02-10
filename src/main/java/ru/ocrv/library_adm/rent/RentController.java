package ru.ocrv.library_adm.rent;

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
public class RentController {

    private final RentService rentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentDtoResponse createRent(@Valid @RequestBody RentDtoRequest request) {
        log.info("rent - Добавление новой аренды");
        return rentService.createRent(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRent(@PathVariable Long id) {
        log.info("rent - Удаление аренды с id: {}", id);
        rentService.deleteRent(id);
    }

    @PatchMapping("/close/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void closeRent(@PathVariable Long id) {
        log.info("rent - Завершение аренды с id: {}", id);
        rentService.closeRent(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentDtoResponse updateRent(@PathVariable Long id,
                                      @Valid @RequestBody RentDtoRequest request) {
        log.info("rent - Обновление аренды с id: {}", id);
        return rentService.updateRent(id, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentDtoResponse getRent(@PathVariable Long id) {
        log.info("rent - Получение аренды с id: {}", id);
        return rentService.getRent(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<RentDtoResponse> getRents(@RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                          @RequestParam(defaultValue = "100") @Positive int size) {
        log.info("rent - Получение списка аренд с позиции {}, количеством {}", from, size);
        return rentService.getRents(from, size);
    }

    @GetMapping("/search/{readerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<RentDtoResponse> searchRentsForReader(@PathVariable Long readerId,
                                             @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                             @RequestParam(defaultValue = "100") @Positive int size) {
        log.info("rent - Поиск аренд по читателю с id: {}", readerId);
        return rentService.getRentsForReader(readerId, from, size);
    }
}
