package ru.ocrv.library_adm.reader.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReaderDtoRequest {

    @NotBlank
    private String passportNumber;

    @NotBlank
    private String passportSeries;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String patronymic;

    @NotBlank
    private String address;

    @Email
    private String email;

    private String phone;
}
