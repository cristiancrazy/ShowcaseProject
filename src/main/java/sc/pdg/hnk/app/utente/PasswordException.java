package sc.pdg.hnk.app.utente;

/**
 * Eccezione legata classe utente relativa
 * a problemi di verifica sulla password dell'utente.
 * @author Cristian, Simone
 */
public class PasswordException extends UserException {
    public PasswordException(String message) {
        super(message);
    }
}
