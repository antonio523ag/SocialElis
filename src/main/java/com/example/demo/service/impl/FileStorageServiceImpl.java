package com.example.demo.service.impl;


import com.example.demo.configuration.FileStorageProperties;
import com.example.demo.exception.FileException;
import com.example.demo.model.Utente;
import com.example.demo.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path pathPrincipale;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        String s=fileStorageProperties.getUploadDir();
        System.out.println(s);
        this.pathPrincipale = Paths.get(s)
                .toAbsolutePath().normalize();

        try {
            Path profilo=pathPrincipale.resolve("profilo");
            Path post=pathPrincipale.resolve("post");
            Files.createDirectories(profilo);
            Files.createDirectories(post);
        } catch (Exception ex) {
            throw new FileException("impossibile creare o caricare la cartella in cui salvare la foto.");
        }
    }

    private boolean salvaFile(MultipartFile file, Path p){
        try {
            Files.copy(file.getInputStream(), p, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public String salvaFotoProfilo(MultipartFile file,Utente u){
        if(file==null)throw  new FileException("impossibile salvare un file vuoto");

        StringBuilder nomeFile= new StringBuilder(u.getId() + "");
        while(nomeFile.length()<15){
            nomeFile.insert(0, "0");
        }
        Path completo = pathPrincipale.resolve("profilo").resolve(nomeFile.toString());
        if(salvaFile(file,completo))return nomeFile.toString();
        else throw new FileException("impossibile salvare il file chiamato "+nomeFile);

    }

    @Override
    public String salvaFotoPost(MultipartFile file,Utente u){
        if(file==null)throw  new FileException("impossibile salvare un file vuoto");
        String nomeFile= u.getEmail().substring(0,u.getEmail().indexOf('@'))+ LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        Path completo = pathPrincipale.resolve("post").resolve(nomeFile);
        if(salvaFile(file,completo))return nomeFile;
        else throw new FileException("impossibile salvare il file chiamato "+nomeFile);
    }

    @Override
    public Resource findFileByNomeAndTipo(String fileName, String tipologia) {
        try {
            Path filePath = this.pathPrincipale.resolve(tipologia).resolve(fileName).normalize();
            System.out.println(filePath);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + fileName);
        }
    }
}