package ru.ocrv.library_adm.rent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "Запрос к эндпоинту аренды")
public class RentDtoRequest {

    @Schema(title = "ID книги", example = "1")
    @NotNull(message = "ID книги должен быть заполнен")
    private Long book;

    @Schema(title = "ID читателя", example = "5")
    @NotNull(message = "ID читателя должен быть заполнен")
    private Long reader;

}
