package ru.ocrv.library_adm.author.srvice;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ocrv.library_adm.author.Author;
import ru.ocrv.library_adm.author.AuthorMapper;
import ru.ocrv.library_adm.author.AuthorStorage;
import ru.ocrv.library_adm.author.QAuthor;
import ru.ocrv.library_adm.author.dto.AuthorDtoRequest;
import ru.ocrv.library_adm.author.dto.AuthorDtoResponse;
import ru.ocrv.library_adm.book.BookStorage;
import ru.ocrv.library_adm.exception.exceptions.AccessDeniedException;
import ru.ocrv.library_adm.exception.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorStorage authorStorage;
    private final BookStorage bookStorage;

    @Override
    public AuthorDtoResponse createAuthor(AuthorDtoRequest request) {
        Author author = authorStorage.save(AuthorMapper.toAuthor(request));
        log.info("Создан автор с id: {}", author.getId());

        return AuthorMapper.toAuthorDtoResponse(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        isAuthorExists(id);
        if (bookStorage.existsBookByAuthorId(id)) {
            throw new AccessDeniedException("Для данного автора есть книги в базе");
        }
        authorStorage.deleteById(id);
        log.info("Удален автор с id: {}", id);
    }

    @Override
    public AuthorDtoResponse updateAuthor(Long id, AuthorDtoRequest request) {
        Author author = findAuthor(id);
        Optional.ofNullable(request.getFirstName()).ifPresent(author::setFirstName);
        Optional.ofNullable(request.getLastName()).ifPresent(author::setLastName);
        Optional.ofNullable(request.getPatronymic()).ifPresent(author::setPatronymic);

        Author updatedAuthor = authorStorage.save(author);
        log.info("Обновлен автор с id: {}", updatedAuthor.getId());

        return AuthorMapper.toAuthorDtoResponse(updatedAuthor);
    }

    @Override
    public AuthorDtoResponse getAuthor(Long id) {
        Author author = findAuthor(id);
        log.info("Прочитан автор с id: {}", author.getId());

        return AuthorMapper.toAuthorDtoResponse(author);
    }

    @Override
    public List<AuthorDtoResponse> searchAuthors(String word, int from, int size) {
        if (StringUtils.isBlank(word)) {
            return new ArrayList<>();
        }

        List<BooleanExpression> conditions = new ArrayList<>();
        conditions.add(QAuthor.author.firstName.containsIgnoreCase(word));
        conditions.add(QAuthor.author.lastName.containsIgnoreCase(word));
        conditions.add(QAuthor.author.patronymic.containsIgnoreCase(word));
        BooleanExpression finalCond = conditions.stream().reduce(BooleanExpression::or).get();

        Pageable pageable = PageRequest.of(from / size, size);
        Page<Author> authors = authorStorage.findAll(finalCond, pageable);
        log.info("Поиск завершен по строке: {}", word);

        return authors.toList()
                .stream()
                .map(AuthorMapper::toAuthorDtoResponse)
                .collect(Collectors.toList());
    }

    private void isAuthorExists(Long id) {
        if (!authorStorage.existsAuthorById(id)) {
            throw new NotFoundException("Автор с id " + id + " не найден");
        }
    }

    private Author findAuthor(Long id) {
        return authorStorage.findById(id)
                .orElseThrow(() -> new NotFoundException("Автор с id " + id + " не найден"));
    }

}


