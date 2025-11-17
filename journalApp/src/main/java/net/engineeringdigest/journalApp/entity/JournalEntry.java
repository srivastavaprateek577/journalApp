package net.engineeringdigest.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.engineeringdigest.journalApp.Enums.Sentiment;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "journal_entries")
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @CreationTimestamp
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Sentiment sentiment;

    @ManyToOne
    @JoinColumn(name = "user_id") // foreign key
    @JsonBackReference
    private User user;
}
