package sc.pdg.hnk.app.utente;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtente {
    @BeforeEach
    public void setUp() {
        // Prima di ogni test, resettare la lista degli utenti
        Utente.setUserList(new HashMap<>());
    }

    @Test
    public void testCreaUtenteValido() throws UserListException, UserCreationException {
        // Test che crea un utente valido
        Utente u = Utente.creaUtente("password123", "test@example.com", "Giovanni");
        assertNotNull(u);
        assertEquals("Giovanni", u.getNome());
        assertEquals("Giovanni", u.toString());
    }

    @Test
    public void testEmailNonValida() {
        // Test con email non valida
        assertThrows(UserCreationException.class, () -> Utente.creaUtente("password123", "invalid-email", "Giovanni"));
    }

    @Test
    public void testPasswordVuota() {
        // Test con password vuota
        assertThrows(UserCreationException.class, () -> Utente.creaUtente("", "test@example.com", "Giovanni"));
    }

    @Test
    public void testNomeVuoto() {
        // Test con nome vuoto
        assertThrows(UserCreationException.class, () -> Utente.creaUtente("password123", "test@example.com", ""));
    }

    @Test
    public void testGiaEsistente() throws UserListException, UserCreationException {
        // Test che tenta di creare un utente con email già esistente
        Utente.creaUtente("password123", "test@example.com", "Giovanni");
        assertThrows(UserListException.class, () -> Utente.creaUtente("password123", "test@example.com", "Simone"));
    }

    @Test
    public void testLoginUtenteValido() throws UserListException, UserCreationException, PasswordException {
        // Test login con email e password corretti
        Utente.creaUtente("password123", "test@example.com", "Giovanni");
        Utente loggedInUser = Utente.loginUtente("test@example.com", "password123");
        assertNotNull(loggedInUser);
        assertEquals("Giovanni", loggedInUser.getNome());
    }

    @Test
    public void testLoginPasswordErrata() throws UserListException, UserCreationException {
        // Test login con password errata
        Utente.creaUtente("password123", "test@example.com", "Giovanni");
        assertThrows(PasswordException.class, () -> Utente.loginUtente("test@example.com", "wrongPassword"));
    }

    @Test
    public void testLoginEmailNonEsistente() {
        // Test login con email non esistente
        assertThrows(UserListException.class, () -> Utente.loginUtente("nonexistent@example.com", "password123"));
    }
}