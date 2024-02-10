package ru.ocrv.library_adm.author;

import lombok.experimental.UtilityClass;
import ru.ocrv.library_adm.author.dto.AuthorDtoRequest;
import ru.ocrv.library_adm.author.dto.AuthorDtoResponse;

@UtilityClass
public class AuthorMapper {

    public static Author toAuthor(AuthorDtoRequest request) {
        return Author.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .patronymic(request.getPatronymic())
                .build();
    }

    public static AuthorDtoResponse toAuthorDtoResponse(Author author) {
        return AuthorDtoResponse.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .patronymic(author.getPatronymic())
                .build();
    }
}
