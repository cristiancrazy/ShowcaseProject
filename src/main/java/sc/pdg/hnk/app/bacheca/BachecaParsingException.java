package sc.pdg.hnk.app.bacheca;

/**
 * Eccezione specifica per i problemi di lettura dei dati serializzati
 * @author Cristian, Simone
 */
public class BachecaParsingException extends BachecaIOException {
    public BachecaParsingException(String message) {
        super(message);
    }
}
