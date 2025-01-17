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
    private Vendita venditaConScadenzaPersonalizzata;

    @BeforeEach
    void setUp() throws AnnuncioException, UserException {
        Utente.setUserList(new HashMap<>());
        // Setup: Creazione dell'utente proprietario
        proprietario = Utente.creaUtente("password123", "test@example.com", "Giovanni");

        // Creazione di un annuncio di vendita valido
        venditaValida = new Vendita("Annuncio di vendita", "Descrizione dell'annuncio", proprietario, "chiave1", 500.0, Vendita.Condizioni.USATO);

        // Creazione di un annuncio di vendita con scadenza personalizzata
        venditaConScadenzaPersonalizzata = new Vendita("Annuncio scadenza", "Descrizione scadenza", proprietario, "chiave2", 1000.0, LocalDate.now().plusDays(10), Vendita.Condizioni.NUOVO);
    }

    @Test
    void testCreazioneAnnuncioVenditaValido() {
        // Verifica che l'annuncio venga creato correttamente
        assertNotNull(venditaValida);
        assertEquals(500.0, venditaValida.getPrezzo());
        assertEquals(Vendita.Condizioni.USATO.name(), venditaValida.getStato());
    }

    @Test
    void testCreazioneAnnuncioVenditaSenzaPrezzo() {
        // Verifica che venga lanciata un'eccezione se il prezzo è null
        assertThrows(AnnuncioException.class, () -> new Vendita("Annuncio senza prezzo", "Descrizione", proprietario, "chiave1,chiave2", null, Vendita.Condizioni.NUOVO));
    }

    @Test
    void testCreazioneAnnuncioVenditaSenzaStato() {
        // Verifica che venga lanciata un'eccezione se lo stato è null
        assertThrows(AnnuncioException.class, () -> new Vendita("Annuncio senza stato", "Descrizione", proprietario, "chiave1,chiave2", 500.0, null));
    }

    @Test
    void testCreazioneAnnuncioConScadenzaPersonalizzata() {
        // Verifica che l'annuncio venga creato correttamente con una scadenza personalizzata
        assertNotNull(venditaConScadenzaPersonalizzata);
        assertEquals(LocalDate.now().plusDays(10), venditaConScadenzaPersonalizzata.getScadenza());
    }

    @Test
    void testScadenzaAnnuncio() {
        // Verifica che l'annuncio venga considerato scaduto se la scadenza è passata
        Vendita venditaScaduta = new Vendita("Annuncio scaduto", "Descrizione", proprietario, "chiave5,chiave6", 300.0, LocalDate.now().minusDays(1), Vendita.Condizioni.MOLTO_USATO);
        assertTrue(venditaScaduta.scaduto());

        // Verifica che l'annuncio non sia scaduto se la scadenza è nel futuro
        assertFalse(venditaValida.scaduto());
    }

    @Test
    void testToString() {
        // Verifica che il metodo toString() restituisca la stringa corretta
        String expectedString = "Vendita: Annuncio di vendita\n\tDescrizione dell'annuncio\n\tStato: USATO\n\tPrezzo: 500,00 EUR\n\tChiavi:chiave1\n\tDi: Giovanni\n";
        assertEquals(expectedString, venditaValida.toString());
    }

    @Test
    void testGetter() {
        // Verifica che i getter restituiscano i valori corretti
        assertEquals(500.0, venditaValida.getPrezzo());
        assertEquals(Vendita.Condizioni.USATO.name().replace("_", " "), venditaValida.getStato());
        assertEquals(proprietario, venditaValida.getProprietario());
        assertTrue(venditaValida.getChiavi().contains("chiave1"));
        assertFalse(venditaValida.getChiavi().contains("chiave2"));
    }
}