package com.example.demo.model;

import com.example.demo.dto.request.CreaClasseDTO;
import com.example.demo.utils.Utilities;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@AllArgsConstructor
@Entity
@Table(name = "classroom")
@NoArgsConstructor
@Getter
@Setter
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "code",unique = true,nullable = false,updatable = false)
    private String codice;
    @Column(name = "name", nullable = false,unique = true)
    private String nome;
    @Column(name = "date_time",updatable = false,insertable = false,nullable = false,columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp dataInserimento;
    @Column(name = "start_date",nullable = false)
    private LocalDate dataInizio;
    @Column(name = "link")
    private String linkEsterno;
    private boolean chiusa;

    public Classe(CreaClasseDTO request) {
        nome= request.getNome();
        codice= Utilities.generaCodice(nome);
        dataInizio=request.getDataInizio();
        linkEsterno= request.getLinkEsterno();
    }
}
