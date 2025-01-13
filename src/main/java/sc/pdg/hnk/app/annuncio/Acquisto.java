package sc.pdg.hnk.app.annuncio;

import sc.pdg.hnk.app.utente.Utente;

import java.io.Serializable;

/**
 * Classe figlia di annuncio, contiene informazioni aggiuntive
 * per la tipologia "Acquisto", come il range di prezzo (min e max range di prezzo)
 * @author Cristian, Simone
 */
public class Acquisto extends Annuncio implements Serializable {

    private Double max = 0D; // Limite superiore
    private Double min = 0D; //Limite inferiore

    public Acquisto(String nome, String descrizione, Utente proprietario, String chiavi, Double max, Double min){
        super(nome, descrizione, proprietario, chiavi);
        this.max = max;
        this.min = min;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    @Override
    public String toString() {
        return String.format("Ricerca: %s\n\t%s\n\tBudget: %.2f-%.2f €\n\tChiavi:%s\n\tDi: %s\n", nome, descrizione, min, max, String.join(",", chiavi), proprietario);
    }
}
