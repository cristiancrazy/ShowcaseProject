package sc.pdg.hnk.app.annuncio;

import sc.pdg.hnk.app.utente.Utente;

public class Acquisto extends Annuncio {
    private Double budget; // Limite superiore

    public Acquisto(String nome, Utente proprietario, String chiave, Double budget){
        super(nome, proprietario, chiave);
        this.budget = budget;
    }

}
