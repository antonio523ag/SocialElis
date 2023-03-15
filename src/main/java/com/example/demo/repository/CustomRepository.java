package com.example.demo.repository;

import com.example.demo.dto.request.CercaUtenteRequest;
import com.example.demo.exception.ValoreNonValidoException;
import com.example.demo.model.Utente;
import com.example.demo.utils.UtilPaths;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class CustomRepository {

    private final EntityManager manager;

    public CustomRepository(EntityManager manager) {
        this.manager = manager;
    }

    public List<Utente> getUtentiFiltrati(CercaUtenteRequest request,Utente u){
        CriteriaBuilder builder=manager.getCriteriaBuilder();
        CriteriaQuery<Utente> query=builder.createQuery(Utente.class);
        Root<Utente> root=query.from(Utente.class);
        List<Predicate> predicate=new ArrayList<>();
        Predicate nome=builder.like(root.get("first_name"),"%"+request.getTestoRicerca()+"%");
        Predicate cognome=builder.like(root.get("last_name"),"%"+request.getTestoRicerca()+"%");
        Predicate email=builder.like(root.get("email"),"%"+request.getTestoRicerca()+"%");
        Predicate username=builder.like(root.get("username"),"%"+request.getTestoRicerca()+"%");
        predicate.add(builder.or(nome,cognome,email,username));
        if(u.getClasse()!=null){
            predicate.add(builder.equal(root.get("id_classroom"),u.getClasse().getId()));
        }else{
            predicate.add(builder.isNull(root.get("id_classroom")));
        }
        query.where(predicate.toArray(new Predicate[predicate.size()]));
        TypedQuery<Utente> queryReale=manager.createQuery(query);
        queryReale.setFirstResult(request.getNumeroDiPagina()*15);
        queryReale.setMaxResults(15);
        return queryReale.getResultList();
    }
}
