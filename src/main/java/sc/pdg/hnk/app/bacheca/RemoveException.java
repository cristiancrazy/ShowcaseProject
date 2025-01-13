package sc.pdg.hnk.app.bacheca;

/**
 * Eccezione specifica per gli errori relativi alla rimozione degli utenti dalla bacheca
 * @author Cristian, Simone
 */
public class RemoveException extends BachecaException {
    public RemoveException(String message) {
        super(message);
    }
}
