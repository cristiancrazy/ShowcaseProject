package sc.pdg.hnk.app.annuncio;

import sc.pdg.hnk.app.utente.Utente;

import java.time.LocalDate;

public class Vendita extends Annuncio {
    private final Double prezzo;
    private final LocalDate scadenza;


    public Vendita(String nome, Utente proprietario, String chiave, Double prezzo) {
        super(nome, proprietario, chiave);
        this.prezzo = prezzo;
        this.scadenza = LocalDate.now().plusMonths(1);
        this.stato=stato;
    }


    public Vendita(String nome, String descrizione, Utente proprietario, String chiave, Double prezzo, LocalDate scadenza, Condizioni stato) {
        super(nome, descrizione, proprietario, chiave);
        this.prezzo = prezzo;
        this.scadenza = scadenza;
    }


    // Check se è scaduto
    public boolean scaduto(){
        return this.scadenza.isBefore(LocalDate.now());
    }

    public String getStato(){
        return this.stato.name();
    }
}
