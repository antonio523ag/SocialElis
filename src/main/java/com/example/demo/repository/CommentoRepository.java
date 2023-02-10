package com.example.demo.repository;

import com.example.demo.model.Commento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentoRepository extends JpaRepository<Commento,Long> {
}
