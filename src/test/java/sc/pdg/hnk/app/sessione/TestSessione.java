package sc.pdg.hnk.app.sessione;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sc.pdg.hnk.app.utente.UserCreationException;
import sc.pdg.hnk.app.utente.UserListException;
import sc.pdg.hnk.app.utente.Utente;

import java.nio.file.Path;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestSessione {
    private Sessione sessione;
    private Utente utente;

    @BeforeEach
    void setUp() throws UserCreationException, UserListException {
        // Inizializza la sessione e l'utente fittizio
        sessione = new Sessione();
        Utente.setUserList(new HashMap<>());
        utente = Utente.creaUtente("password123", "test@example.com", "Giovanni");
    }

    @Test
    void testSetCurrentUser() {
        // Testa che l'utente corrente possa essere impostato e recuperato correttamente
        sessione.setCurrentUser(utente);
        assertEquals(utente, sessione.getCurrentUser());
    }

    @Test
    void testGetFileBackupPredefinito() {
        // Testa che il percorso del file di backup sia il valore predefinito
        Path expectedPath = Path.of("./bacheca.dat");
        assertEquals(expectedPath, sessione.getFileBackup());
    }

    @Test
    void testSetFileBackup() {
        // Testa che il percorso del file di backup possa essere modificato
        Path newPath = Path.of("./nuovo_backup.dat");
        sessione.setFileBackup(newPath);
        assertEquals(newPath, sessione.getFileBackup());
    }

    @Test
    void testSetCurrentUserNull() {
        // Testa che l'utente corrente possa essere impostato su null
        sessione.setCurrentUser(null);
        assertNull(sessione.getCurrentUser());
    }
}
