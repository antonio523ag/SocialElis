package com.example.demo.service.impl;

import com.example.demo.dto.request.CommentaPostRequest;
import com.example.demo.dto.request.IdCommentoRequest;
import com.example.demo.exception.NessunCommentoException;
import com.example.demo.exception.NessunPermessoVisualizzazioneException;
import com.example.demo.model.Commento;
import com.example.demo.model.Post;
import com.example.demo.model.Ruolo;
import com.example.demo.model.Utente;
import com.example.demo.repository.CommentoRepository;
import com.example.demo.service.CommentoService;
import com.example.demo.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class CommentoServiceImpl implements CommentoService {

    private final CommentoRepository repo;
    private final PostService servicePost;

    public CommentoServiceImpl(CommentoRepository repo, PostService servicePost) {
        this.repo = repo;
        this.servicePost = servicePost;
    }

    @Override
    public void commentaPost(CommentaPostRequest request, Utente u) {
        Post p=servicePost.findById(request.getIdPost());
        if(p.getCreatore().getClasse()==null&&u.getClasse()!=null)throw new NessunPermessoVisualizzazioneException("impossibile commentare questo post, non si dispone dei diritti");
        if(p.getCreatore().getClasse()!=null&&u.getClasse()==null&&u.getRuolo()!= Ruolo.GESTORE)throw new NessunPermessoVisualizzazioneException("impossibile commentare questo post, non si dispone dei diritti");
        if(p.getCreatore().getClasse()!=null&&u.getRuolo()!=Ruolo.GESTORE&&u.getClasse().getId()!=p.getCreatore().getClasse().getId())throw new NessunPermessoVisualizzazioneException("impossibile commentare questo post, non si dispone dei diritti");
        Commento c=new Commento(0, request.getCommento(), null,p,u);
        repo.save(c);
    }

    @Override
    public void cancellaPost(IdCommentoRequest request, Utente u) {
        Commento c=repo.findById(request.getId()).orElseThrow(NessunCommentoException::new);
        if(u.getRuolo()!=Ruolo.GESTORE&&c.getUtente().getId()!=u.getId()&&c.getPost().getCreatore().getId()!=u.getId())throw new NessunPermessoVisualizzazioneException("il commento non è stato scritto dall'utente selezionato");
        repo.delete(c);
    }
}
