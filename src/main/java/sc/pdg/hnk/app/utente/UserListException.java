package sc.pdg.hnk.app.utente;

/**
 * Eccezione legata alla classe utente e relativa
 * alla mancanza o alla presenza (duplicata) di un utente nel sistema.
 * @author Cristian, Simone
 */
public class UserListException extends UserException {
    public UserListException(String message) {
        super(message);
    }
}
