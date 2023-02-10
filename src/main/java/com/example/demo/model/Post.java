package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    @Column(name = "text", length = 512)
    private String testo;
    @Column(name = "date_time",updatable = false,insertable = false,nullable = false,columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp dataInserimento;
    @Column(name = "image_url")
    private String urlImmagine;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user",nullable = false,updatable = false)
    private Utente creatore;
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Commento> commenti;
}
