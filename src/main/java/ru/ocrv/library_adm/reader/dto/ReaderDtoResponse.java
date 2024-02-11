package ru.ocrv.library_adm.reader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "Ответ от эндпоинта читателей")
public class ReaderDtoResponse {

    @Schema(title = "ID", example = "1")
    private Long id;

    @Schema(title = "Номер паспорта", example = "123456")
    private String passportNumber;

    @Schema(title = "Серия паспорта", example = "11 22")
    private String passportSeries;

    @Schema(title = "Имя", example = "Иван")
    private String firstName;

    @Schema(title = "Фамилия", example = "Иванов")
    private String lastName;

    @Schema(title = "Отчество", example = "Иванович")
    private String patronymic;

    @Schema(title = "Физический адрес", example = "ул. Пушкина 15-23")
    private String address;

    @Schema(title = "Электронная почта", example = "ivanov@mail.ru")
    private String email;

    @Schema(title = "Телефон", example = "+78456731122")
    private String phone;
}
