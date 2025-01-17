package sc.pdg.hnk.app.utente;

/**
 * Eccezione specifica dellper la creazione dell'utente
 */
public class UserCreationException extends UserException {
    public UserCreationException(String message) {
        super(message);
    }
}
