package sc.pdg.hnk.app.sessione;

import sc.pdg.hnk.app.utente.Utente;

import java.nio.file.Path;

/**
 * Classe relativa allo stoccaggio dei dati relativi alla sessione utente dell'applicazione
 * @author Cristian, Simone
 */
public class Sessione{

    private Utente utenteCorrente;
    private Path fileBackup = Path.of("./bacheca.dat");

    /**
     * Set per l'utente corrente con quello che ha effettuato il login
     * @param utenteCorrente Utente che ha effettuato il login con successo
     */
    public void setCurrentUser(Utente utenteCorrente) {
        this.utenteCorrente = utenteCorrente;
    }

    /**
     * Estrapolazione dell'utente corrente
     * @return L'utente corrente
     */
    public Utente getCurrentUser() {
        return utenteCorrente;
    }

    /**
     * Specifica il Path del file che contiene la bacheca e gli utenti
     * @return il path del file
     */
    public Path getFileBackup() {
        return fileBackup;
    }

    /**
     * Valorizza il Path con un valore personalizzato
     * @param fileBackup il path del file da specificare
     */
    public void setFileBackup(Path fileBackup) {
        this.fileBackup = fileBackup;
    }
}
