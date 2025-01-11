package sc.pdg.hnk.app.bacheca;

/**
 * Eccezione specifica per gli errori relativi alla rimozione degli utenti dalla bacheca
 */
public class RemoveException extends BachecaException {
    public RemoveException(String message) {
        super(message);
    }
}
