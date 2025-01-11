package sc.pdg.hnk.app.utente;

/**
 * Eccezione legata alla classe utente e relativa
 * alla mancanza di un utente nel sistema.
 */
public class UserListException extends UserException {
    public UserListException(String message) {
        super(message);
    }
}
