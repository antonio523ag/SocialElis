package com.example.demo.service.impl;

import com.example.demo.dto.general.PostDTO;
import com.example.demo.dto.request.IdPostRequest;
import com.example.demo.dto.request.IdUtenteRequest;
import com.example.demo.dto.request.NumeroPaginaRequest;
import com.example.demo.dto.response.ListaUtentiLike;
import com.example.demo.dto.response.VisualizzaPostDTO;
import com.example.demo.exception.LikeNonTrovatoException;
import com.example.demo.exception.NessunPermessoVisualizzazioneException;
import com.example.demo.exception.PostException;
import com.example.demo.exception.PostNonTrovatoException;
import com.example.demo.model.Post;
import com.example.demo.model.Ruolo;
import com.example.demo.model.Utente;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.PostService;
import com.example.demo.service.UtenteService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository repo;
    private final UtenteService utenteService;
    private final FileStorageService fileService;

    public PostServiceImpl(PostRepository repo, UtenteService utenteService, FileStorageService fileService) {
        this.repo = repo;
        this.utenteService = utenteService;
        this.fileService = fileService;
    }

    @Override
    public List<Post> findAllByIdClasse(Utente u) {
        return null;
    }

    @Override
    public List<Post> findAllByIdCreatore(IdUtenteRequest request) {
        return null;
    }

    @Override
    public PostDTO salvaPost(String request, Utente u) {
        return salvaPost(request,null,u);
    }

    @Override
    public PostDTO salvaPost(String request, MultipartFile file, Utente u) {
        String path=file==null?null:fileService.salvaFotoPost(file,u);
        Post p=new Post(0,request, null,path,u,null,null);
        p=repo.save(p);
        return new PostDTO(p);
    }

    @Override
    public VisualizzaPostDTO visualizzaPostPerUtente(IdUtenteRequest request, Utente u) {
        Utente u1=utenteService.getUtenteById(request);
        if(u.getRuolo()!= Ruolo.GESTORE){
            if(u.getClasse()==null&&u1.getClasse()!=null)throw new NessunPermessoVisualizzazioneException("non puoi visualizzare i post di persone che sono ancora in ambiente di testing");
            else if(u.getClasse()!=null&&u1.getClasse()==null) throw new NessunPermessoVisualizzazioneException("sei ancora in ambiente di testing, non puoi visualizzare i post in ambiente comune");
            else if(u.getClasse()!=null && u.getClasse().getId()!=u1.getClasse().getId())throw new NessunPermessoVisualizzazioneException("non siete nella stessa classe, non puoi visualizzare i post di questo utente");
        }
        Pageable p= PageRequest.of(request.getPage(), 20);
        Page<Post> l=repo.findAllByCreatore_IdOrderByDataInserimentoDesc(request.getId(), p);
        List<PostDTO> list=l.get().map(PostDTO::new).toList();
        return new VisualizzaPostDTO(list, request.getPage(), l.getTotalPages()-1);

    }

    @Override
    public VisualizzaPostDTO visualizzaPostHome(NumeroPaginaRequest r, Utente u){
        Pageable pageable = PageRequest.of(r.getNumeroPagina(),20);
        Page<Post> p=u.getClasse()!=null?repo.findAllByCreatore_Classe_IdOrderByDataInserimentoDesc(u.getClasse().getId(), pageable):repo.findAllByCreatore_ClasseIsNullOrdOrderByDataInserimentoDesc(pageable);
        System.out.println("p.getTotalPages() "+p.getTotalPages());
        System.out.println("p.getTotalElements() "+p.getTotalElements());
        System.out.println("p.getNumber() "+p.getNumber());
        System.out.println("p.getNumberOfElements() "+ p.getNumberOfElements());
        return new VisualizzaPostDTO(p.get().map(PostDTO::new).toList(),r.getNumeroPagina(),p.getTotalPages()-1);
    }

    @Override
    public void cancellaPost(IdPostRequest request, Utente u) {
        Post p=repo.findById(request.getId()).orElseThrow(()-> new PostException(request.getId()));
        if(p.getCreatore().getId()!=u.getId()&&u.getRuolo()!=Ruolo.GESTORE)throw new PostException(u.getUsername());
        repo.delete(p);
    }

    @Override
    public void mettiLikeAPost(IdPostRequest request, Utente u) {
        Post p=repo.findById(request.getId()).orElseThrow(PostNonTrovatoException::new);
        if(p.getMiPiace()==null)p.setMiPiace(new ArrayList<>());
        if(u.getMiPiace()==null)u.setMiPiace(new ArrayList<>());
        if(u.getMiPiace().stream().map(u1->u.getId()).anyMatch(u1->u.getId()==u1))throw new LikeNonTrovatoException();
        p.getMiPiace().add(u);
        u.getMiPiace().add(p);
        repo.save(p);
    }

    @Override
    public void rimuoviLikeDaPost(IdPostRequest request, Utente u) {
        Post p=repo.findById(request.getId()).orElseThrow(PostNonTrovatoException::new);
        if(p.getMiPiace()==null||u.getMiPiace()==null)throw new LikeNonTrovatoException();
        if(u.getMiPiace().stream().map(u1->u.getId()).noneMatch(u1->u.getId()==u1))throw new LikeNonTrovatoException();
        p.getMiPiace().removeIf(u1->u1.getId()==u.getId());
        u.getMiPiace().removeIf(p1->p1.getId()==p.getId());
        repo.save(p);
    }

    @Override
    public ListaUtentiLike visualizzaLike(IdPostRequest request) {
        Post p=repo.findById(request.getId()).orElseThrow(PostNonTrovatoException::new);
        return new ListaUtentiLike(p.getMiPiace());
    }

    @Override
    public Post findById(long idPost) {
        return repo.findById(idPost).orElseThrow(PostNonTrovatoException::new);
    }


}
