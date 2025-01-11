package sc.pdg.hnk.app.bacheca;

/**
 * Eccezione specifica per gli errori di Input Output
 */
public class BachecaIOException extends BachecaException{
    public BachecaIOException(String message) {
        super(message);
    }
}
