package sc.pdg.hnk.app.annuncio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sc.pdg.hnk.app.utente.UserException;
import sc.pdg.hnk.app.utente.Utente;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;

public class TestVendita {

    private Utente proprietario;
    private Vendita venditaValida;
    private Vendita venditaConScadenza;

    @BeforeEach
    void setUp() throws AnnuncioException, UserException {
        Utente.setUserList(new HashMap<>());

        proprietario = Utente.creaUtente("password123", "test@example.com", "Giovanni");
        venditaValida = new Vendita("Annuncio di vendita", "Descrizione dell'annuncio", proprietario, "chiave1", 500.0, Vendita.Condizioni.USATO);
        venditaConScadenza = new Vendita("Annuncio scadenza", "Descrizione scadenza", proprietario, "chiave2", 1000.0, LocalDate.now().plusDays(10), Vendita.Condizioni.NUOVO);
    }

    @Test
    void testCreazioneVendita() {
        // Verifica che l'annuncio venga creato correttamente
        assertNotNull(venditaValida);
        assertEquals(500.0, venditaValida.getPrezzo());
        assertEquals(Vendita.Condizioni.USATO.name(), venditaValida.getStato());
    }

    @Test
    void testCreazioneSenzaPrezzo() {
        // Verifica che venga lanciata un'eccezione se il prezzo è null
        assertThrows(AnnuncioException.class, () -> new Vendita("Annuncio senza prezzo", "Descrizione", proprietario, "chiave1,chiave2", null, Vendita.Condizioni.NUOVO));
    }

    @Test
    void testCreazioneSenzaStato() {
        // Verifica che venga lanciata un'eccezione se lo stato è null
        assertThrows(AnnuncioException.class, () -> new Vendita("Annuncio senza stato", "Descrizione", proprietario, "chiave1,chiave2", 500.0, null));
    }

    @Test
    void testCreazioneScadenzaPersonalizzata() {
        // Verifica che l'annuncio venga creato correttamente con una scadenza personalizzata
        assertNotNull(venditaConScadenza);
        assertEquals(LocalDate.now().plusDays(10), venditaConScadenza.getScadenza());
    }

    @Test
    void testScadenzaAnnuncio() {
        // Verifica che l'annuncio venga considerato scaduto
        Vendita venditaScaduta = new Vendita("Annuncio scaduto", "Descrizione", proprietario, "chiave5,chiave6", 300.0, LocalDate.now().minusDays(1), Vendita.Condizioni.MOLTO_USATO);
        assertTrue(venditaScaduta.scaduto());

        // Verifica che l'annuncio non sia scaduto
        assertFalse(venditaValida.scaduto());
    }

    @Test
    void testToString() {
        // Verifica stringa corretta
        String expectedString = "Vendita: Annuncio di vendita\n\tDescrizione dell'annuncio\n\tStato: USATO\n\tPrezzo: 500,00 EUR\n\tChiavi:chiave1\n\tDi: Giovanni\n";
        assertEquals(expectedString, venditaValida.toString());
    }

    @Test
    void testGetter() {
        assertEquals(500.0, venditaValida.getPrezzo());
        assertEquals(Vendita.Condizioni.USATO.name().replace("_", " "), venditaValida.getStato());
        assertEquals(proprietario, venditaValida.getProprietario());
        assertTrue(venditaValida.getChiavi().contains("chiave1"));
        assertFalse(venditaValida.getChiavi().contains("chiave2"));
    }
}