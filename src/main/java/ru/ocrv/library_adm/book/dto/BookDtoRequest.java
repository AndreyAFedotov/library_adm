package ru.ocrv.library_adm.book.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "Запрос к эндпоинту книг")
public class BookDtoRequest {

    @Schema(title = "ID автора", example = "1")
    @NotNull(message = "Автор должен быть заполнен")
    private Long author;

    @Schema(title = "Заголовок", example = "Евгений Онегин")
    @NotBlank(message = "Заголовок должен быть заполнен")
    @Length(min = 2, max = 500)
    private String title;

    @Schema(title = "Издательство", example = "Издательство 1")
    @NotBlank(message = "Издательство должно быть заполнено")
    @Length(min = 2, max = 500)
    private String publishing;

    @Schema(title = "Дата издания", example = "2025-01-01 00:00:00")
    @NotNull(message = "Дата публикации должна быть заполнена")
    @PastOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishingDate;
}
