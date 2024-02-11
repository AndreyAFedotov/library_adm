package ru.ocrv.library_adm.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "Ответ от эндпоинта автора")
public class AuthorDtoResponse {

    @Schema(title = "ID", example = "1")
    private Long id;

    @Schema(title = "Имя", example = "Иван")
    private String firstName;

    @Schema(title = "Фамилия", example = "Иванов")
    private String lastName;

    @Schema(title = "Отчество", example = "Иванович")
    private String patronymic;

}
