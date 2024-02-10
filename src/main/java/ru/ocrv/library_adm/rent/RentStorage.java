package ru.ocrv.library_adm.rent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentStorage extends JpaRepository<Rent, Long> {

}
