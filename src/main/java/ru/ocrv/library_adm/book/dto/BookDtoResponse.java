package ru.ocrv.library_adm.book.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.ocrv.library_adm.author.dto.AuthorDtoResponse;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "Ответ от эндпоинта книг")
public class BookDtoResponse {

    @Schema(title = "ID", example = "1")
    private Long id;

    @Schema(title = "Автор")
    private AuthorDtoResponse author;

    @Schema(title = "Заголовок", example = "Евгений Онегин")
    private String title;

    @Schema(title = "Издательство", example = "Издательство 1")
    private String publishing;

    @Schema(title = "Дата издания", example = "2025-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishingDate;

    @Schema(title = "Находится в аренде", example = "true")
    private Boolean rented;
}
