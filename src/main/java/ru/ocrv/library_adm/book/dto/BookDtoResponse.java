package ru.ocrv.library_adm.book.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.ocrv.library_adm.author.Author;
import ru.ocrv.library_adm.author.dto.AuthorDtoResponse;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDtoResponse {

    private Long id;

    private AuthorDtoResponse author;

    private String title;

    private String publishing;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishingDate;

    private Boolean rented;
}
