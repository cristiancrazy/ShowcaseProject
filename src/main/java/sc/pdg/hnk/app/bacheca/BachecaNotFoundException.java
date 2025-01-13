package sc.pdg.hnk.app.bacheca;

/**
 * Eccezione specifica per il fallimento della procedura di ricerca del file nel path
 * @author Cristian, Simone
 */
public class BachecaNotFoundException extends BachecaIOException {
    public BachecaNotFoundException(String message) {
        super(message);
    }
}
