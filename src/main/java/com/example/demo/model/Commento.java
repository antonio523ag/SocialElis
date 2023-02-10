package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@Entity
@Table(name = "comment")
@NoArgsConstructor
@Getter
@Setter
public class Commento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "text",nullable = false)
    private String testo;
    @Column(name = "date_time",updatable = false,insertable = false,nullable = false,columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp dataInserimento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_post", nullable = false, updatable = false)
    private Post post;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false,updatable = false)
    private Utente utente;
}
