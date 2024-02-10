package ru.ocrv.library_adm.book;

import lombok.experimental.UtilityClass;
import ru.ocrv.library_adm.author.Author;
import ru.ocrv.library_adm.author.AuthorMapper;
import ru.ocrv.library_adm.book.dto.BookDtoRequest;
import ru.ocrv.library_adm.book.dto.BookDtoResponse;

@UtilityClass
public class BookMapper {
    public static Book toBook(Author author, BookDtoRequest request) {
        return Book.builder()
                .author(author)
                .title(request.getTitle())
                .publishing(request.getPublishing())
                .publishingDate(request.getPublishingDate())
                .build();
    }

    public static BookDtoResponse toBookDtoResponse(Book book) {
        return BookDtoResponse.builder()
                .id(book.getId())
                .author(AuthorMapper.toAuthorDtoResponse(book.getAuthor()))
                .title(book.getTitle())
                .publishing(book.getPublishing())
                .publishingDate(book.getPublishingDate())
                .rented(book.getRented())
                .build();
    }
}
