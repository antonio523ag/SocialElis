package com.example.demo.utils;

public interface UtilPaths {

    interface General{
        String GENERAL="all";
        String LOGIN="login";
        String REGISTRAZIONE="registrazione";
        String RECUPERA_PASSWORD="recuperaPassword";
        String CONFERMA_RICHIESTA_NUOVA_PASSWORD="confermaNuovaPassword";
    }

    interface Admin{
        String ADMIN="admin";
        String APRI_AULA="apriAula";
        String CHIUDI_AULA="chiudiAula";
        String VISUALIZZA_RICHIESTE="visualizzaRichieste";
        String ACCETTA_RICHIESTA="accettaRichiesta";
        String ACCETTA_RICHIESTE_BULK="accettaRichiesteBulk";
    }

    interface Studente {
        String STUDENTE="studente";
        String MODIFICA_PROFILO="modificaProfilo";
        String AGGIUNGI_IMMAGINE="aggiungiImmagine";
        String CERCA_UTENTE="cercaUtente";
        String VISUALIZZA_FOTO_PROFILO="fotoProfilo";
        String VISUALIZZA_FOTO_POST="fotoPost";
        String VISUALIZZA_POST="visualizzaPost";
        String VISUALIZZA_POST_UTENTE="visualizzaPostUtente";
        String SCRIVI_POST="scriviPost";
        String ELIMINA_POST="eliminaPost";
        String COMMENTA_POST="commentaPost";
        String ELIMINA_COMMENTO="eliminaCommento";
        String METTI_LIKE_A_POST="mettiLikeAPost";
        String RIMUOVI_LIKE_A_POST="rimuoviLikeAPost";
        String VISUALIZZA_LIKE_DEL_POST="visualizzaLikeDelPost";
    }

}
