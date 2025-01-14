package sc.pdg.hnk.app.annuncio;

import sc.pdg.hnk.app.utente.Utente;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Super-classe astratta che contiene
 * le informazioni di base degli annunci di acquisto e di vendita.
 * @author Cristian, Simone
 */
public abstract class Annuncio implements Serializable {

    private final String IDAnnuncio; // Identificativo univoco dell'annuncio
    protected String nome;
    protected String descrizione;
    protected Utente proprietario;
    protected Set<String> chiavi; // Stringa di parole chiave

    /**
     * Creazione dell'annuncio.
     * @param nome nome dell'annuncio
     * @param descrizione descrizione dell'annuncio
     * @param proprietario specifica l'utente proprietario dell'annuncio
     * @param chiavi stringa di parole chiave separate da virgola.
     */
    public Annuncio(String nome, String descrizione, Utente proprietario, String chiavi){
        this.nome = nome;
        this.proprietario = proprietario;
        this.chiavi = chiaviToLista(chiavi);
        this.IDAnnuncio = nuovoID();
        this.descrizione = descrizione;
    }

    /**
     * Aggiunge una nuova parola chiave all'annuncio
     * @param chiave nuova parola chiave
     */
    public void aggiungiChiave(String chiave){
        this.chiavi.add(chiave);
    }

    /**
     * Effettua lo splitting di una stringa di parole chiave
     * @param chiavi stringa di parole chiave separate da virgola.
     * @return restituisce un set di parole chiave
     */
    public static Set<String> chiaviToLista(String chiavi){
        return new HashSet<>(Arrays.stream(chiavi.split(",")).map(String::trim).toList());
    }

    /**
     * Crea un nuovo ID univoco da associare all'annuncio.
     * @return l'ID dell'annuncio come stringa
     */
    private static String nuovoID(){
        String ldt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-H:m:s:S.n"));
        String hash = "";
        try{
            hash = new String(MessageDigest.getInstance("SHA-256").digest(ldt.getBytes(StandardCharsets.UTF_8)));
        }catch (NoSuchAlgorithmException ignored){}
        return hash;
    }

    /**
     * Verifica se l'utente è proprietario dell'annuncio
     * @param p l'utente da verificare
     * @return restituisce true se l'utente è il proprietario
     */
    public boolean isProprietario(Utente p){
        return p.equals(proprietario);
    }

    /**
     * Restituisce l'ID univoco dell'annuncio
     * @return l'ID dell'annuncio come stringa.
     */
    public String getIDAnnuncio() {
        return IDAnnuncio;
    }

    /**
     * Restituisce il set di parole chiave dell'annuncio
     * @return set di stringhe di parole chiave.
     */
    public Set<String> getChiavi() {
        return chiavi;
    }
}
