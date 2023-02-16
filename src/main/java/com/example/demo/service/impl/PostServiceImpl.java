package com.example.demo.service.impl;

import com.example.demo.dto.general.PostDTO;
import com.example.demo.dto.request.IdUtenteRequest;
import com.example.demo.dto.request.NuovoPostRequest;
import com.example.demo.model.Post;
import com.example.demo.model.Utente;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.PostService;
import com.example.demo.service.UtenteService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
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
    public PostDTO salvaPost(NuovoPostRequest request, MultipartFile file, Utente u) {
        String path=fileService.salvaFotoPost(file,u);
        Post p=new Post(0,request.getTesto(), null,path,u,null,null);
        p=repo.save(p);
        return new PostDTO(p);
    }
}
