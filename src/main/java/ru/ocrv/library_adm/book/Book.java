package ru.ocrv.library_adm.book;

import jakarta.persistence.*;
import lombok.*;
import ru.ocrv.library_adm.author.Author;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "publishing", nullable = false)
    private String publishing;

    @Column(name = "publishing_date", nullable = false)
    private LocalDateTime publishingDate;

    @Column(name = "rented")
    private Boolean rented;
}
