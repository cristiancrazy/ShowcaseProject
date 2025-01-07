package sc.pdg.hnk.app.utente;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Objects;


public class Utente implements Serializable {
    /* Lista utenti del programma */
    private static HashMap<String, Utente> UserList = new HashMap<>();

    private final String email;
    private final String password; // Hashed password
    private final String nome;

    public static Utente creaUtente(String password,String email, String nome) {
        // Istanza del nuovo utente
        Utente var = new Utente(password, email, nome);

        // Aggiunta alla mappa
        UserList.put(email, var);

        return var;
    }

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

    @Override
    public String toString() {
        return String.format("Email: %s\nNome: %s", this.email, this.nome);
    }

}
