package ru.ocrv.library_adm.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookStorage extends JpaRepository<Book, Long>, QuerydslPredicateExecutor<Book> {

    Boolean existsBookById(Long id);

    Boolean existsBookByAuthorId(Long authorId);

    Page<Book> searchBooksByAuthorId(Long aurhoId, Pageable pageable);

    Boolean existsBookByIdAndRented(Long id, Boolean rented);

}
