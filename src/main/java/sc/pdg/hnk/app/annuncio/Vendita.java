package sc.pdg.hnk.app.annuncio;

import sc.pdg.hnk.app.utente.Utente;

import java.time.LocalDate;

public class Vendita extends Annuncio {
    private final Double prezzo;
    private final LocalDate scadenza;
    private final Condizioni stato;

    public enum Condizioni {
        NUOVO,
        USATO,
    }

    public Vendita(String nome, String descrizione , Utente proprietario, String chiave, Double prezzo, Condizioni stato) {
        super(nome, descrizione,proprietario, chiave);
        this.prezzo = prezzo;
        this.scadenza = LocalDate.now().plusMonths(1);
        this.stato=stato;
    }


    public Vendita(String nome, String descrizione, Utente proprietario, String chiave, Double prezzo, LocalDate scadenza, Condizioni stato) {
        super(nome, descrizione, proprietario, chiave);
        this.prezzo = prezzo;
        this.scadenza = scadenza;
        this.descrizione = descrizione;
        this.stato=stato;
    }


    // Check se è scaduto
    public boolean scaduto(){
        return this.scadenza.isBefore(LocalDate.now());
    }

    public String getStato(){
        return this.stato.name();
    }
}
