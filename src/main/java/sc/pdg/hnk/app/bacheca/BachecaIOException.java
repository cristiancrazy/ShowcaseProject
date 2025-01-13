package sc.pdg.hnk.app.bacheca;

/**
 * Eccezione specifica per gli errori di I/O della bacheca
 * @author Cristian, Simone
 */
public class BachecaIOException extends BachecaException{
    public BachecaIOException(String message) {
        super(message);
    }
}
