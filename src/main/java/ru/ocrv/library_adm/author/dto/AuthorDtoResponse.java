package ru.ocrv.library_adm.author.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDtoResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String patronymic;

}
