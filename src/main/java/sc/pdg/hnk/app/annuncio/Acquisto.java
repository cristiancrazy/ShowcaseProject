package sc.pdg.hnk.app.annuncio;

import sc.pdg.hnk.app.utente.Utente;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe figlia di annuncio, contiene informazioni aggiuntive
 * per la tipologia "Acquisto", come il range di prezzo (min e max range di prezzo)
 * @author Cristian, Simone
 */
public class Acquisto extends Annuncio implements Serializable {

    private final Double max; // Limite superiore
    private final Double min; //Limite inferiore

    /**
     * Costruttore della classe
     * @param nome nome dell'annuncio
     * @param descrizione descrizione dell'annuncio
     * @param proprietario proprietario dell'annuncio
     * @param chiavi Stringa con separatore contenente le chiavi
     * @param max prezzo massimo
     * @param min prezzo minimo
     * @throws AnnuncioException lanciata nel caso in cui i valori non siano validi
     */
    public Acquisto(String nome, String descrizione, Utente proprietario, String chiavi, Double max, Double min) throws AnnuncioException{
        super(nome, descrizione, proprietario, chiavi);

        // Verifica correttezza budget minimo e massimo
        if(min > max){
            this.max = min;
            this.min = max;
        }else{
            this.max = max;
            this.min = min;
        }


    }

    /**
     * getter Min
     * @return Val Min
     */
    public Double getMin() {
        return min;
    }

    /**
     * getter Max
     * @return Val Max
     */
    public Double getMax() {
        return max;
    }

    @Override
    public String toString() {
        return String.format("Ricerca: %s\n\t%s\n\tBudget: %.2f-%.2f EUR\n\tChiavi:%s\n\tDi: %s\n", nome, descrizione, min, max, String.join(",", chiavi), proprietario);
    }

}
