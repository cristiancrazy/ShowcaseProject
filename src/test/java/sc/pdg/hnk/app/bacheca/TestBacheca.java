package sc.pdg.hnk.app.bacheca;

import org.junit.jupiter.api.*;
import sc.pdg.hnk.app.annuncio.Acquisto;
import sc.pdg.hnk.app.annuncio.Annuncio;
import sc.pdg.hnk.app.annuncio.Vendita;
import sc.pdg.hnk.app.utente.UserException;
import sc.pdg.hnk.app.utente.Utente;

import javax.xml.xpath.XPathEvaluationResult;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestBacheca {
    private static final Path fpSalvataggio = Path.of("./bacheca_test.dat");
    private static final Utente proprietario;
    private static final Acquisto acquisto;
    private static final Vendita vendita;
    private static final Vendita venditaScaduta;

    static {
        // Eliminazione utenti
        Utente.setUserList(new HashMap<>());

        // Creazione di un utente
        try {
            proprietario = Utente.creaUtente("password123", "test@example.com", "Giovanni");
        } catch (UserException e) {
            throw new RuntimeException(e);
        }

        // Creazione di un annuncio di tipo Acquisto
        acquisto = new Acquisto("Acquisto Laptop", "Cerco un laptop", proprietario, "laptop, usato", 1000.0, 500.0);

        // Creazione di un annuncio di tipo Vendita
        vendita = new Vendita("Vendita Laptop", "Vendo un laptop", proprietario, "laptop, usato", 700.0, Vendita.Condizioni.USATO);

        // Creazione di un annuncio di tipo Vendita con scadenza
        venditaScaduta = new Vendita("Vendita TV", "Vendo una TV", proprietario, "TV, usato", 300.0, LocalDate.now().minusDays(1), Vendita.Condizioni.MOLTO_USATO);
    }

    @Test
    @Order(1)
    void testAggiungiVendita() {
        //Verifica che l'aggiunta dell'annuncio avvenga correttamente
        assertNull(Bacheca.aggiungiAnnuncio(vendita));
        assertNull(Bacheca.aggiungiAnnuncio(venditaScaduta));
        assertEquals(2, Bacheca.getBacheca().size());
    }

    @Test
    @Order(2)
    void testAggiungiAcquisto() {
        // Verifica che l'aggiunta di un annuncio di tipo Acquisto ritorni gli annunci relativi
        assertEquals(2, Bacheca.getBacheca().size());

        List<Annuncio> risultati = Bacheca.aggiungiAnnuncio(acquisto);
        assertNotNull(risultati);
        assertFalse(risultati.isEmpty());
        assertEquals(1, risultati.size());

        assertEquals(3, Bacheca.getBacheca().size());
    }

    @Test
    @Order(3)
    void testPulisciBacheca() {
        //Verifica la pulizia della bacheca
        Bacheca.pulisciBacheca();
        assertEquals(2, Bacheca.getBacheca().size());
    }

    @Test
    @Order(4)
    void testFiltraProprietario() {
        //Verifica che il proprietario dell'annuncio venga riconosciuto
        assertEquals(2, Bacheca.getBacheca().size());
        List<Annuncio> risultati = Bacheca.filtraProprietario(Bacheca.getBacheca(), proprietario);
        assertEquals(2, risultati.size()); // Tutti gli annunci appartengono al proprietario
    }

    @Test
    @Order(5)
    void testFiltraTipo() {
        //Verifica che i filtri funzionino correttamente
        assertEquals(2, Bacheca.getBacheca().size());
        List<Annuncio> ricerca = Bacheca.filtraTipo(Bacheca.getBacheca(), Acquisto.class);
        List<Annuncio> vendita = Bacheca.filtraTipo(Bacheca.getBacheca(), Vendita.class);
        assertEquals(1, ricerca.size());
        assertEquals(1, vendita.size());
    }

    @Test
    @Order(6)
    void testRicercaChiavi() {
        //Verifica che per le chiavi vengano restituiti solo gli annunci pertinenti
        List<Annuncio> laptop = Bacheca.ricerca(Bacheca.getBacheca(), Annuncio.chiaviToLista("usato,laptop"));
        assertEquals(2, laptop.size());
        List<Annuncio> usati = Bacheca.ricerca(Bacheca.getBacheca(), Annuncio.chiaviToLista("usato"));
        assertEquals(2, usati.size());
        List<Annuncio> nuovo = Bacheca.ricerca(Bacheca.getBacheca(), Annuncio.chiaviToLista("nuovo"));
        assertEquals(0 ,nuovo.size());
    }

    @Test
    @Order(7)
    void testRimuoviAnnuncio() throws RemoveException {
        // Verifica che l'annuncio venga rimosso correttamente
        boolean rimosso = Bacheca.rimuoviAnnuncio(vendita.getIDAnnuncio(), proprietario);
        assertTrue(rimosso);
        assertEquals(1, Bacheca.getBacheca().size()); // La bacheca ora dovrebbe avere 1 annuncio

        // Rimettiamo l'annuncio
        Bacheca.aggiungiAnnuncio(vendita);
        // Verifica che venga sollevata un'eccezione se l'utente non è il proprietario
        assertThrows(RemoveException.class, () -> Bacheca.rimuoviAnnuncio(vendita.getIDAnnuncio(), Utente.creaUtente("password456", "altro@example.com", "Marco")));
    }

    @Test
    @Order(8)
    void testSalvataggioBacheca() throws BachecaIOException {
        // Verifica che il salvataggio avvenga senza errori
        Bacheca.salvataggio(fpSalvataggio);
    }

    @Test
    @Order(9)
    void testRecuperoBacheca() throws Exception {
        Bacheca.recupero(fpSalvataggio);
        assertEquals(2, Bacheca.getBacheca().size());

        assertThrows(BachecaNotFoundException.class, () -> Bacheca.recupero(Path.of("./file_non_esistente_bacheca.dat")));

        // Crea file non valido
        Path parsingErrFile = Path.of("./test_lancio_parsing_error.dat");
        parsingErrFile.toFile().createNewFile();
        Files.writeString(parsingErrFile, "Contenuto_Del_File_Errato", StandardOpenOption.WRITE);
        assertThrows(BachecaIOException.class, () -> Bacheca.recupero(parsingErrFile));
        parsingErrFile.toFile().deleteOnExit();
    }

    @Test
    @Order(10)
    void testIterazioneBacheca() {
        // Verifica che l'iterazione sulla bacheca funzioni
        int count = 0;
        for (Annuncio ignored : Bacheca.getBacheca()) {
            count++;
        }
        assertEquals(2, count);
    }
}