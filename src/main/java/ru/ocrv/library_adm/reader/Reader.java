package ru.ocrv.library_adm.reader;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "readers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "passport_number", nullable = false)
    private String passportNumber;

    @Column(name = "passport_series", nullable = false)
    private String passportSeries;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

}
