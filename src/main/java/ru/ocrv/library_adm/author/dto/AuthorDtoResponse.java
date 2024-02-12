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

    @Schema(title = "Имя", example = "Александр")
    private String firstName;

    @Schema(title = "Отчество", example = "Сергеевич")
    private String patronymic;

    @Schema(title = "Фамилия", example = "Пушкин")
    private String lastName;

}
