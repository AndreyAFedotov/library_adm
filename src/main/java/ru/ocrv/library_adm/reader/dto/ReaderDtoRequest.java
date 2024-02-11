package ru.ocrv.library_adm.reader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "Запрос к эндпоинту читателей")
public class ReaderDtoRequest {

    @Schema(title = "Номер паспорта", example = "123456")
    @NotBlank(message = "Номер паспорта должен быть заполнен")
    private String passportNumber;

    @Schema(title = "Серия паспорта", example = "11 22")
    @NotBlank(message = "Серия паспорта должна быть заполнена")
    private String passportSeries;

    @Schema(title = "Имя", example = "Иван")
    @NotBlank(message = "Имя должно быть заполнено")
    private String firstName;

    @Schema(title = "Фамилия", example = "Иванов")
    @NotBlank(message = "Должна быть заполнена")
    private String lastName;

    @Schema(title = "Отчество", example = "Иванович")
    private String patronymic;

    @Schema(title = "Физический адрес", example = "ул. Пушкина 15-23")
    @NotBlank(message = "Физический адрес должен быть заполнен")
    private String address;

    @Schema(title = "Электронная почта", example = "ivanov@mail.ru")
    @Email
    private String email;

    @Schema(title = "Телефон", example = "+79266543455")
    private String phone;
}
