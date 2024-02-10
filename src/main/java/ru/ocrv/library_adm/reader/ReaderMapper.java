package ru.ocrv.library_adm.reader;

import lombok.experimental.UtilityClass;
import ru.ocrv.library_adm.reader.dto.ReaderDtoRequest;
import ru.ocrv.library_adm.reader.dto.ReaderDtoResponse;

@UtilityClass
public class ReaderMapper {
    public static Reader toReader(ReaderDtoRequest request) {
        return Reader.builder()
                .passportNumber(request.getPassportNumber())
                .passportSeries(request.getPassportSeries())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .patronymic(request.getPatronymic())
                .address(request.getAddress())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
    }

    public static ReaderDtoResponse toReaderDtoResponse(Reader reader) {
        return ReaderDtoResponse.builder()
                .id(reader.getId())
                .passportNumber(reader.getPassportNumber())
                .passportSeries(reader.getPassportSeries())
                .firstName(reader.getFirstName())
                .lastName(reader.getLastName())
                .patronymic(reader.getPatronymic())
                .address(reader.getAddress())
                .email(reader.getEmail())
                .phone(reader.getPhone())
                .build();
    }
}
