package ru.ocrv.library_adm.reader.service;

import ru.ocrv.library_adm.reader.dto.ReaderDtoRequest;
import ru.ocrv.library_adm.reader.dto.ReaderDtoResponse;

import java.util.List;

public interface ReaderService {

    ReaderDtoResponse createReader(ReaderDtoRequest request);

    void deleteReader(Long id);

    ReaderDtoResponse updateReader(Long id, ReaderDtoRequest request);

    ReaderDtoResponse getReader(Long id);

    List<ReaderDtoResponse> searchReaders(String word, int from, int size);

}
