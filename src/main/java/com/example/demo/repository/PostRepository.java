package com.example.demo.repository;

import com.example.demo.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository   extends JpaRepository<Post,Long> {
    Page<Post> findAllByCreatore_Classe_IdOrderByDataInserimentoDesc(long id, Pageable p);
    Page<Post> findAllByCreatore_IdOrderByDataInserimentoDesc(long id,Pageable p);
    @Query("select p from Post p where p.creatore is null order by p.dataInserimento desc")
    Page<Post> findAllByCreatore_ClasseIsNullOrdOrderByDataInserimentoDesc(Pageable p);
}
