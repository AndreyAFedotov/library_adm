package ru.ocrv.library_adm.reader.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReaderDtoResponse {

    private Long id;

    private String passportNumber;

    private String passportSeries;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String address;

    private String email;

    private String phone;
}
