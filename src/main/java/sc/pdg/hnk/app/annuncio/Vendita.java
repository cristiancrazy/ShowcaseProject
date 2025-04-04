package sc.pdg.hnk.app.annuncio;

import sc.pdg.hnk.app.utente.Utente;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Vendita permette di rappresentare e operare su informazioni
 * aggiuntive legate agli Annunci di vendita.
 * @author Cristian, Simone
 */
public class Vendita extends Annuncio implements Serializable {

    private final Double prezzo;
    private final LocalDate scadenza;
    private final Condizioni stato;

    /**
     * Rappresenta le condizioni dell'oggetto
     * messo in vendita.
     */
    public enum Condizioni {
        NUOVO,
        COME_NUOVO,
        USATO,
        MOLTO_USATO,
        NON_FUNZIONANTE
    }


    /**
     * Creazione dell'annuncio di vendita se non è specificata la data di scadenza
     * @param nome nome dell'annuncio
     * @param descrizione descrizione dell'annuncio
     * @param proprietario specifica l'utente proprietario dell'annuncio
     * @param chiavi stringa di parole chiave separate da virgola.
     * @param prezzo prezzo dell'oggetto in vendita
     * @param stato stato di usura dell'oggetto in vendita.
     * @throws AnnuncioException lanciata nel caso in cui i valori non siano validi
     */
    public Vendita(String nome, String descrizione, Utente proprietario, String chiavi, Double prezzo, Condizioni stato) throws AnnuncioException {
        super(nome, descrizione, proprietario, chiavi);

        // Check validità campi
        if(prezzo == null){
            throw new AnnuncioException("Non è stato specificato il prezzo");
        }
        if(stato == null){
            throw new AnnuncioException("Non è stata specificata la condizione");
        }

        this.prezzo = prezzo;
        this.scadenza = LocalDate.now().plusMonths(1);
        this.stato=stato;
    }

    /**
     * Creazione dell'annuncio di vendita
     * @param nome nome dell'annuncio
     * @param descrizione descrizione dell'annuncio
     * @param proprietario specifica l'utente proprietario dell'annuncio
     * @param chiavi stringa di parole chiave separate da virgola.
     * @param prezzo prezzo dell'oggetto in vendita
     * @param scadenza data di scadenza dell'annuncio in vendita
     * @param stato stato di usura dell'oggetto in vendita
     */
    public Vendita(String nome, String descrizione, Utente proprietario, String chiavi, Double prezzo, LocalDate scadenza, Condizioni stato) throws AnnuncioException {
        super(nome, descrizione, proprietario, chiavi);

        // Check validità campi
        if(prezzo == null){
            throw new AnnuncioException("Non è stato specificato il prezzo");
        }
        if(stato == null){
            throw new AnnuncioException("Non è stata specificata la condizione");
        }
        if(scadenza == null){
            throw new AnnuncioException("Non è stata specificata una scadenza");
        }

        this.prezzo = prezzo;
        this.scadenza = scadenza;
        this.descrizione = descrizione;
        this.stato = stato;
    }


    /**
     * Verifica se l'annuncio è scaduto.
     * @return true se l'annuncio è già scaduto.
     */
    public boolean scaduto(){
        return this.scadenza.isBefore(LocalDate.now());
    }

    /**
     * Restituisce lo stato dell'oggetto messo in vendita
     * @return stato dell'oggetto come stringa.
     */
    public String getStato(){
        return this.stato.name().replace("_", " ");
    }

    @Override
    public String toString() {
        return String.format("Vendita: %s\n\t%s\n\tStato: %s\n\tPrezzo: %.2f EUR\n\tChiavi:%s\n\tDi: %s\n", nome, descrizione, this.getStato(), prezzo, String.join(",", chiavi), proprietario);
    }

    /**
     * Getter per il prezzo
     * @return Prezzo dell'annuncio
     */
    public Double getPrezzo() {
        return prezzo;
    }

    /**
     * Getter per la scadenza
     */
    public LocalDate getScadenza() {
        return scadenza;
    }
}
