package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class Utente implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "first_name",nullable = false,updatable = false)
    private String nome;
    @Column(name = "last_name",nullable = false,updatable = false)
    private String cognome;
    @Column(nullable = false,unique = true,updatable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String username;
    @Column(name = "image")
    private String pathImg;
    @Column(name = "role")
    private Ruolo ruolo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_classroom")
    private Classe classe;
    @Column(nullable = false,name = "ultimo_accesso")
    private LocalDateTime ultimoAccesso;
    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean bloccato;
    @ManyToMany
    @JoinTable(name = "user_post_like",
            joinColumns = @JoinColumn(name = "id_user",updatable = false,nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_post",updatable = false,nullable = false),uniqueConstraints = @UniqueConstraint(columnNames={"id_user","id_post"}))
    private List<Post> miPiace;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ruolo.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return ultimoAccesso.plusMonths(6).isAfter(LocalDateTime.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !bloccato;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}