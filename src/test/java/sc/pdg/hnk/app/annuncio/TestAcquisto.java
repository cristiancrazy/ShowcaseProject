package sc.pdg.hnk.app.annuncio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sc.pdg.hnk.app.utente.UserException;
import sc.pdg.hnk.app.utente.Utente;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TestAcquisto {

    private Utente proprietario;
    private Acquisto acquistoValido;
    private Acquisto acquistoInvertito;
    private Acquisto acquistoConBudgetCorretto;

    @BeforeEach
    void setUp() throws AnnuncioException, UserException {
        // Setup: Creazione dell'utente proprietario
        Utente.setUserList(new HashMap<>());
        proprietario = Utente.creaUtente("password123", "test@example.com", "Giovanni");

        // Creazione di acquisti validi
        acquistoValido = new Acquisto("Annuncio 1", "Descrizione dell'annuncio", proprietario, "chiave2,chiave1", 1000.0, 500.0);
        acquistoInvertito = new Acquisto("Annuncio 2", "Descrizione dell'annuncio invertita", proprietario, "chiave3,chiave4", 500.0, 1000.0);
        acquistoConBudgetCorretto = new Acquisto("Annuncio 3", "Descrizione corretta", proprietario, "chiave5,chiave6", 2000.0, 1500.0);
    }

    @Test
    void testCreazioneAnnuncioAcquisto() {
        // Verifica che l'annuncio venga creato correttamente
        assertNotNull(acquistoValido);
        assertEquals("Annuncio 1", acquistoValido.getNome());
        assertEquals(500.0, acquistoValido.getMin());
        assertEquals(1000.0, acquistoValido.getMax());
    }

    @Test
    void testCreazioneBudgetInvertito() {
        // Verifica che i valori min e max siano corretti anche se inseriti in ordine invertito
        assertNotNull(acquistoInvertito);
        assertEquals(500.0, acquistoInvertito.getMin());
        assertEquals(1000.0, acquistoInvertito.getMax());
    }

    @Test
    void testCreazioneBudgetCorretto() {
        // Verifica che l'annuncio con il bilancio corretto venga creato senza problemi
        assertNotNull(acquistoConBudgetCorretto);
        assertEquals(1500.0, acquistoConBudgetCorretto.getMin());
        assertEquals(2000.0, acquistoConBudgetCorretto.getMax());
    }

    @Test
    void testToString() {
        // Verifica metodo toString()
        String expectedString = "Ricerca: Annuncio 1\n\tDescrizione dell'annuncio\n\tBudget: 500,00-1000,00 EUR\n\tChiavi:chiave2,chiave1\n\tDi: Giovanni\n";
        assertEquals(expectedString, acquistoValido.toString());
    }

    @Test
    void testGetter() {
        // Verifica i getter
        assertEquals("Annuncio 1", acquistoValido.getNome());
        assertEquals("Descrizione dell'annuncio", acquistoValido.getDescrizione());
        assertEquals(proprietario, acquistoValido.getProprietario());
        assertTrue(acquistoValido.getChiavi().contains("chiave1"));
        assertTrue(acquistoValido.getChiavi().contains("chiave2"));
    }

    @Test
    void testIsProprietario() {
        assertTrue(acquistoValido.isProprietario(proprietario));
    }
}
