package ru.ocrv.library_adm.author.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDtoRequest {

    @NotBlank
    @Length(min = 2, max = 100)
    private String firstName;

    @Length(min = 2, max = 100)
    private String lastName;

    @Length(min = 2, max = 100)
    private String patronymic;
}
