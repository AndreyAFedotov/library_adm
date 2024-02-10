package ru.ocrv.library_adm.rent;

import jakarta.persistence.*;
import lombok.*;
import ru.ocrv.library_adm.book.Book;
import ru.ocrv.library_adm.reader.Reader;

import java.time.LocalDateTime;

@Entity
@Table(name = "rents")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
}
