package ru.ocrv.library_adm.rent.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.ocrv.library_adm.book.dto.BookDtoResponse;
import ru.ocrv.library_adm.reader.dto.ReaderDtoResponse;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "Ответ от эндпоинта аренды")
public class RentDtoResponse {

    @Schema(title = "ID", example = "1")
    private Long id;

    @Schema(title = "Книга")
    private BookDtoResponse book;

    @Schema(title = "Читатель")
    private ReaderDtoResponse reader;

    @Schema(title = "Момент начала аренды", example = "2025-01-01 11:22:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @Schema(title = "Момент окончания аренды", example = "2025-01-10 10:05:15")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
}
