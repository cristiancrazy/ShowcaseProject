package sc.pdg.hnk.app.annuncio;

import sc.pdg.hnk.app.utente.Utente;

public class Acquisto extends Annuncio {
    private Double budget; // Limite superiore
    private Double min; //Limite inferiore

    public Acquisto(String nome, String descrizione, Utente proprietario, String chiave, Double budget, Double min){
        super(nome, descrizione, proprietario, chiave);
        this.budget = budget;
        this.min = min;
    }
}
