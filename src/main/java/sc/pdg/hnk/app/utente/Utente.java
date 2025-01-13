package sc.pdg.hnk.app.utente;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Objects;

/**
 * Utente rappresenta un utente all'interno dell'applicazione.
 * Offre metodi che permettono di creare ed effettuare operazioni con l'utente,
 * oltre a contenere la lista degli utenti.
 * @author Cristian, Simone
 */
public class Utente implements Serializable {

    /* Lista utenti del programma */
    private static HashMap<String, Utente> UserList = new HashMap<>();

    private final String email;
    private final String password; // Hashed password
    private final String nome;

    /**
     * Creazione di un nuovo utente e inserimento dell'utente nella
     * lista degli utenti dell'applicazione.
     * @param password password del nuovo utente
     * @param email email del nuovo utente
     * @param nome nome del nuovo utente
     * @throws UserListException nel caso in cui l'utente esiste già.
     * @return restituisce l'oggetto Utente
     */
    public static Utente creaUtente(String password,String email, String nome) throws UserListException {
        // Istanza del nuovo utente
        Utente u = new Utente(password, email, nome);

        // Aggiunta alla mappa
        if (UserList.putIfAbsent(email, u) != null){
            throw new UserListException("L'utente esiste già");
        }

        return u;
    }

    /**
     * Funzione che permette di effettuare il login dell'utente
     * @param email l'email dell'utente
     * @param password la password dell'utente
     * @return restituisce l'oggetto utente a cui fanno riferimento email e password
     * @throws PasswordException lanciata se l'utente esiste ma la password non è corretta
     * @throws UserListException lanciata se l'utente non esiste nella lista utenti dell'app.
     */
    public static Utente loginUtente(String email,String password) throws PasswordException, UserListException {
        Utente utente = UserList.get(email);

        if(utente != null) {
            if (Objects.equals(passwordHash(password), utente.password)) {
                return UserList.get(email);
            } else {
                throw new PasswordException("Password non corretta :-(");
            }
        }else{
            throw new UserListException("Utente non esistente :-(");
        }
    }

    /**
     * Effettua l'operazione di HASH della password con SHA-256
     * @param password la password dell'utente
     * @return restituisce la rappresentazione in stringa dall'operazione di HASH
     */
    private static String passwordHash(String password){
        String hash = "";
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = new String(digest.digest(password.getBytes(StandardCharsets.UTF_8)));
        }catch (NoSuchAlgorithmException ignored){}
        return hash;
    }

    /**
     * Creazione di un nuovo utente
     * @param password la password dell'utente
     * @param email la mail dell'utente
     * @param nome il nome dell'utente
     */
    private Utente(String password,String email, String nome){
        this.email=email;
        this.password= passwordHash(password);
        this.nome=nome;
    }

    /**
     * Restituisce informazioni utili alla stampa dei dati utente
     * @return stringa di informazioni dell'utente.
     */
    @Override
    public String toString() {
        return String.format("%s", this.nome);
    }

    /**
     * Restituisce i dati della lista utenti
     * @return la lista utenti
     */
    public static HashMap<String, Utente> getUserList() {
        return UserList;
    }

    /**
     * Setta la lista utenti dopo la lettura dei dati
     * @param userList la lista utenti
     */
    public static void setUserList(HashMap<String, Utente> userList) {
        UserList = userList;
    }
}
