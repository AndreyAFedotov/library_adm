package ru.ocrv.library_adm.book.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDtoRequest {

    @NotBlank
    private Long author;

    @NotBlank
    @Length(min = 2, max = 500)
    private String title;

    @NotBlank
    @Length(min = 2, max = 500)
    private String publishing;

    @NotBlank
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishingDate;
}
