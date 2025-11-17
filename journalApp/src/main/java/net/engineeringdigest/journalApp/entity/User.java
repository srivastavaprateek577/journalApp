package net.engineeringdigest.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, columnDefinition="varchar(255) default ''")
    private String email;

    @Column(nullable = false, columnDefinition="boolean default false")
    private Boolean sentimentAnalysis = false;

    @Column(nullable = false)
    private String password;

    // One user → Many journal entries
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"title", "content", "date", "user"})
    @JsonManagedReference
    private List<JournalEntry> journalEntries = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
}
