package sc.pdg.hnk.app.bacheca;

/**
 * Eccezione specifica per i problemi di lettura dei dati serializati
 */
public class BachecaParsingException extends BachecaIOException {
    public BachecaParsingException(String message) {
        super(message);
    }
}
