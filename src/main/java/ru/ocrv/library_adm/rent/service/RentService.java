package ru.ocrv.library_adm.rent.service;

import ru.ocrv.library_adm.rent.dto.RentDtoRequest;
import ru.ocrv.library_adm.rent.dto.RentDtoResponse;

import java.util.List;

public interface RentService {

    RentDtoResponse createRent(RentDtoRequest request);

    void closeRent(Long id);

    void deleteRent(Long id);

    RentDtoResponse updateRent(Long id, RentDtoRequest request);

    List<RentDtoResponse> getRents (int from, int size);

    List<RentDtoResponse> getRentsForReader(Long readerId, int from, int size);

    RentDtoResponse getRent(Long id);
}
