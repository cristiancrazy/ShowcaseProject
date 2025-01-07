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

    }


    public Vendita(String nome, Utente proprietario, String chiave, Double prezzo, LocalDate scadenza) {
        super(nome, proprietario, chiave);
        this.prezzo = prezzo;
        this.scadenza = scadenza;
    }

}
