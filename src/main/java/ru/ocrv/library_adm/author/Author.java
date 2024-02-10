package ru.ocrv.library_adm.author;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

}
