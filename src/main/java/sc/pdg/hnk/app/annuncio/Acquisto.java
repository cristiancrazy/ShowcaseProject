package sc.pdg.hnk.app.annuncio;

import sc.pdg.hnk.app.utente.Utente;

/**
 * Classe figlia di annuncio ,implementa budget massimo e minimo
 */
public class Acquisto extends Annuncio {
    private Double budget; // Limite superiore
    private Double min; //Limite inferiore

    public Acquisto(String nome, String descrizione, Utente proprietario, String chiavi, Double budget, Double min){
        super(nome, descrizione, proprietario, chiavi);
        this.budget = budget;
        this.min = min;
    }
}
