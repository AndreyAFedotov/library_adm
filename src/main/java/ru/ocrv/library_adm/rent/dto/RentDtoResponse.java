package ru.ocrv.library_adm.rent.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.ocrv.library_adm.book.Book;
import ru.ocrv.library_adm.book.dto.BookDtoResponse;
import ru.ocrv.library_adm.reader.Reader;
import ru.ocrv.library_adm.reader.dto.ReaderDtoResponse;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentDtoResponse {

    private Long id;

    private BookDtoResponse book;

    private ReaderDtoResponse reader;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
}
