package ru.ocrv.library_adm.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AuthorStorage extends JpaRepository<Author, Long>, QuerydslPredicateExecutor<Author> {

    Boolean existsAuthorById(Long id);

}
