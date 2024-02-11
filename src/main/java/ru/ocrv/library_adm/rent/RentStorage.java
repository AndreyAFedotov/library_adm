package ru.ocrv.library_adm.rent;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface RentStorage extends JpaRepository<Rent, Long> {

    Page<Rent> findRentsByReaderId(Long readerId, Pageable pageable);

    Boolean existsByReaderIdAndEndDate(Long readerId, LocalDateTime endDate);
}
