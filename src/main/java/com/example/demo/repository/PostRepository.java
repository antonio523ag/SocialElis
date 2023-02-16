package com.example.demo.repository;

import com.example.demo.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository   extends JpaRepository<Post,Long> {
    Page<Post> findAllByCreatore_Classe_Id(long id, Pageable p);
    Page<Post> findAllByCreatore_Id(long id,Pageable p);
}
