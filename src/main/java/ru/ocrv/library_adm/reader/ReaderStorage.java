package ru.ocrv.library_adm.reader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReaderStorage extends JpaRepository<Reader, Long>, QuerydslPredicateExecutor<Reader> {

    Boolean existsReaderById(Long id);

}
