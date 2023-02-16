package com.example.demo.service;

import com.example.demo.model.Utente;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface FileStorageService {
    Resource findFileByNomeAndTipo(String fileName, String tipologia);
    String salvaFotoProfilo(MultipartFile file,Utente u);
    String salvaFotoPost(MultipartFile file,Utente u);
}
