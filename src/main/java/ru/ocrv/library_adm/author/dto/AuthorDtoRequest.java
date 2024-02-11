package ru.ocrv.library_adm.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "Запрос к эндпоинту авторов")
public class AuthorDtoRequest {

    @Schema(title = "Имя", example = "Иван")
    @NotBlank(message = "Имя должно быть заполнено")
    @Length(min = 2, max = 100)
    private String firstName;

    @Schema(title = "Фамилия", example = "Иванов")
    @Length(min = 2, max = 100)
    private String lastName;

    @Schema(title = "Отчество", example = "Иванович")
    @Length(min = 2, max = 100)
    private String patronymic;
}
