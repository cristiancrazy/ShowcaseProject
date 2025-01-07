package sc.pdg.hnk.app.annuncio;

import sc.pdg.hnk.app.utente.Utente;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Annuncio implements Serializable {
    private final String IDAnnuncio; // Identificativo univoco dell'annuncio
    protected String nome;
    protected Utente proprietario;
    protected String chiave; // Stringa di parole chiave

    public Annuncio(String nome, Utente proprietario, String chiave){
        this.nome = nome;
        this.proprietario = proprietario;
        this.chiave = chiave;
        this.IDAnnuncio = nuovoID();
    }

    private String nuovoID(){
        String ldt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-H:m:s:S.n"));
        String hash = "";
        try{
            hash = new String(MessageDigest.getInstance("SHA-256").digest(ldt.getBytes(StandardCharsets.UTF_8)));
        }catch (NoSuchAlgorithmException ignored){};
        return hash;
    }


    public String getIDAnnuncio() {
        return IDAnnuncio;
    }
}
