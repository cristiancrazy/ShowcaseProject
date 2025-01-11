package sc.pdg.hnk.app.sessione;

import sc.pdg.hnk.app.utente.Utente;

/**
 * Classe relativa allo stoccaggio dei dati relativi all'utente loggato
 */
public class Sessione{
    private Utente utenteCorrente;
    /**
     * Funzione per settare l'utente corrente con quello che ha effettuato il login
     * @param utenteCorrente Utente che ha effettuato il login con successo
     */
    public void setCurrentUser(Utente utenteCorrente) {
        this.utenteCorrente = utenteCorrente;
    }
    /**
     * Funzione per l'estrapolazione dell'utente corrente
     * @return L'utente corrente
     */
    public Utente getCurrentUser() {
        return utenteCorrente;
    }
}
