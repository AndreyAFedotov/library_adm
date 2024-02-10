package ru.ocrv.library_adm.rent;

import ru.ocrv.library_adm.book.Book;
import ru.ocrv.library_adm.book.BookMapper;
import ru.ocrv.library_adm.reader.Reader;
import ru.ocrv.library_adm.reader.ReaderMapper;
import ru.ocrv.library_adm.rent.dto.RentDtoResponse;

import java.time.LocalDateTime;

public class RentMapper {
    public static Rent toRent(Book book, Reader reader, LocalDateTime startDate, LocalDateTime endDate) {
        return Rent.builder()
                .book(book)
                .reader(reader)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

    public static RentDtoResponse toRentDtoResponse(Rent rent) {
        return RentDtoResponse.builder()
                .id(rent.getId())
                .book(BookMapper.toBookDtoResponse(rent.getBook()))
                .reader(ReaderMapper.toReaderDtoResponse(rent.getReader()))
                .startDate(rent.getStartDate())
                .endDate(rent.getEndDate())
                .build();
    }
}
