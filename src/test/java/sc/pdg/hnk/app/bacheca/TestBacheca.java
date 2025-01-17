package sc.pdg.hnk.app.bacheca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sc.pdg.hnk.app.annuncio.Acquisto;
import sc.pdg.hnk.app.annuncio.Annuncio;
import sc.pdg.hnk.app.annuncio.AnnuncioException;
import sc.pdg.hnk.app.annuncio.Vendita;
import sc.pdg.hnk.app.sessione.Sessione;
import sc.pdg.hnk.app.utente.UserException;
import sc.pdg.hnk.app.utente.Utente;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;
import java.time.LocalDate;

public class TestBacheca {
    private Sessione sessione ;
    private Utente proprietario;
    private Acquisto acquisto;
    private Vendita vendita;

    @BeforeEach
    void setUp() throws AnnuncioException, UserException {
        // Eliminazione utenti
        Utente.setUserList(new HashMap<>());

        Path path = Path.of("./bacheca_test.dat");
        if(path.toFile().exists()){
            path.toFile().delete();
        }
        this.sessione = new Sessione();
        sessione.setFileBackup(path);

        // Creazione di un utente
        proprietario = Utente.creaUtente("password123", "test@example.com", "Giovanni");

        // Creazione di un annuncio di tipo Acquisto
        acquisto = new Acquisto("Acquisto Laptop", "Cerco un laptop", proprietario, "laptop, usato", 1000.0, 500.0);

        // Creazione di un annuncio di tipo Vendita
        vendita = new Vendita("Vendita Laptop", "Vendo un laptop", proprietario, "laptop, usato", 700.0, Vendita.Condizioni.USATO);

        // Creazione di un annuncio di tipo Vendita con scadenza
        Vendita venditaScaduta = new Vendita("Vendita TV", "Vendo una TV", proprietario, "TV, usato", 300.0, LocalDate.now().minusDays(1), Vendita.Condizioni.MOLTO_USATO);

        // Aggiunta degli annunci alla bacheca
        Bacheca.aggiungiAnnuncio(acquisto);
        Bacheca.aggiungiAnnuncio(vendita);
        Bacheca.aggiungiAnnuncio(venditaScaduta);
    }

    @Test
    void testAggiungiAnnuncioVendita() {
        // Verifica che l'annuncio venga aggiunto correttamente alla bacheca
        List<Annuncio> bacheca = Bacheca.getBacheca();
        bacheca.forEach(System.out::println);
        assertEquals(3, bacheca.size());
    }

    @Test
    void testAggiungiAnnuncioAcquisto() {
        // Verifica che l'aggiunta di un annuncio di tipo Acquisto ritorni la lista di annunci compatibili
        List<Annuncio> risultati = Bacheca.aggiungiAnnuncio(acquisto);
        assertNotNull(risultati);
        assertFalse(risultati.isEmpty());
    }

    @Test
    void testPulisciBacheca() {

    }

    @Test
    void testFiltraProprietario() {
        // Verifica che gli annunci vengano filtrati correttamente per proprietario
        List<Annuncio> risultati = Bacheca.filtraProprietario(Bacheca.getBacheca(), proprietario);
        assertEquals(3, risultati.size()); // Tutti gli annunci appartengono al proprietario
    }

    @Test
    void testFiltraTipo() {
    }

    @Test
    void testRicercaChiavi() {

    }

    @Test
    void testRimuoviAnnuncio() throws RemoveException {
        // Verifica che l'annuncio venga rimosso correttamente
        boolean rimosso = Bacheca.rimuoviAnnuncio(vendita.getIDAnnuncio(), proprietario);
        assertTrue(rimosso);
        assertEquals(2, Bacheca.getBacheca().size()); // La bacheca ora dovrebbe avere 2 annunci

        // Verifica che venga sollevata un'eccezione se l'utente non è il proprietario
        assertThrows(RemoveException.class, () -> Bacheca.rimuoviAnnuncio(vendita.getIDAnnuncio(), Utente.creaUtente("password456", "altro@example.com", "Marco")));
    }

    @Test
    void testSalvataggioBacheca() throws BachecaIOException {
        // Verifica che il salvataggio della bacheca in un file avvenga senza errori
        Bacheca.salvataggio(Paths.get("bacheca_test.dat"));
    }

    @Test
    void testRecuperoBacheca() throws Exception {
        // Verifica che il recupero della bacheca da un file avvenga senza errori
        Path file = Paths.get("bacheca_test.dat");
        Bacheca.salvataggio(file);
        Bacheca.recupero(file);
        assertEquals(3, Bacheca.getBacheca().size()); // Verifica che la bacheca recuperata abbia 3 annunci
    }

    @Test
    void testIterazioneBacheca() {
        // Verifica che l'iterazione sulla bacheca funzioni correttamente
        int count = 0;
        for (Annuncio ignored : Bacheca.getBacheca()) {
            count++;
        }
        assertEquals(3, count); // Dovrebbero esserci 3 annunci nella bacheca
    }
}
