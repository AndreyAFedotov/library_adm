package ru.ocrv.library_adm.author.srvice;

import ru.ocrv.library_adm.author.dto.AuthorDtoRequest;
import ru.ocrv.library_adm.author.dto.AuthorDtoResponse;

import java.util.List;

public interface AuthorService {

    AuthorDtoResponse createAuthor(AuthorDtoRequest request);

    void deleteAuthor(Long id);

    AuthorDtoResponse updateAuthor(Long id, AuthorDtoRequest request);

    AuthorDtoResponse getAuthor(Long id);

    List<AuthorDtoResponse> searchAuthors(String word, int from, int size);

}
