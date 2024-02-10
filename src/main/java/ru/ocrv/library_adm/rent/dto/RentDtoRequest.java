package ru.ocrv.library_adm.rent.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentDtoRequest {

    private Long id;

    @NotBlank
    private Long book;

    @NotBlank
    private Long reader;

}
