package sc.pdg.hnk.gui;

import sc.pdg.hnk.app.annuncio.Annuncio;
import sc.pdg.hnk.app.bacheca.Bacheca;
import sc.pdg.hnk.app.bacheca.BachecaIOException;
import sc.pdg.hnk.app.bacheca.BachecaNotFoundException;
import sc.pdg.hnk.app.bacheca.RemoveException;
import sc.pdg.hnk.app.sessione.Sessione;
import sc.pdg.hnk.app.utente.PasswordException;
import sc.pdg.hnk.app.utente.UserCreationException;
import sc.pdg.hnk.app.utente.UserListException;
import sc.pdg.hnk.app.utente.Utente;

import javax.swing.*;
import java.nio.file.Path;
import java.util.List;

public class ComandiGUI {
    private static final Sessione sessione = new Sessione();
    private static BachecaFrame bacheca = new BachecaFrame();

    /**
     * Carica da file dal path corrente e lancia la GUI
     */
    public static void lanciaGUI(){
        load();
        new LoginFrame().setVisible(true);
    }

    /**
     *  Carica da file dal path specificato e lancia la GUI
     */
    public static void lanciaGUI(Path fileSalvataggio){
        sessione.setFileBackup(fileSalvataggio);
        load();
        new LoginFrame().setVisible(true);
    }

    /**
     * Costruttore vuoto
     */
    private ComandiGUI(){ }

    /**
     *  Caricamento bacheca e lista utenti da file
     */
    static void load(){
        boolean daCreare = false;
        try{
            Bacheca.recupero(sessione.getFileBackup());
        }catch (BachecaNotFoundException e){
            daCreare = true;
        } catch (BachecaIOException e) {
            System.out.println(e.getMessage());
        }

        if(daCreare) store();
    }

    /**
     * Salvataggio bacheca e lista utenti sul file
     */
    static void store(){
        try{
            Bacheca.salvataggio(sessione.getFileBackup());
        }catch (BachecaIOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Rende visibile la bacheca principale
     */
    static void lanciaBacheca(){
        bacheca = new BachecaFrame();
        bacheca.setVisible(true);
        caricaAnnunci(Bacheca.getBacheca());
    }

    /**
     * Ricarica gli annunci dalla bacheca
     */
    static void ricaricaBacheca(){
        caricaAnnunci(Bacheca.getBacheca());
    }

    /**
     * Cancella tutti gli annunci scaduti
     */
    static void pulisciBacheca(){
        Bacheca.pulisciBacheca();
        ricaricaBacheca();
        JOptionPane.showMessageDialog(null, "Sono stati cancellati tutti gli annunci scaduti.", "Pulizia completata", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Carica gli annunci in bacheca
     * @param annunci lista di annunci (bacheca)
     */
    private static void caricaAnnunci(List<Annuncio> annunci){
        bacheca.getPanelAnnunci().removeAll();

        for(Annuncio a : annunci){
            bacheca.getPanelAnnunci().add(new AnnuncioPanel(a));
        }
    }

    /**
     * Carica nella sezione rimuovi gli annunci eliminabili dall'utente
     */
    static void caricaAnnunciModificabili(){
        bacheca.getPanelAnnunci().removeAll();

        for(Annuncio a : Bacheca.filtraProprietario(Bacheca.getBacheca(), sessione.getCurrentUser())){
            AnnuncioPanel panel = new AnnuncioPanel(a);
            panel.abilitaModifica();
            bacheca.getPanelAnnunci().add(panel);
        }
    }

    /**
     * Aggiunge un annuncio in bacheca
     * @param annuncio annuncio da aggiungere
     */
    static void aggiungiAnnuncio(Annuncio annuncio){
        Bacheca.aggiungiAnnuncio(annuncio);
        ricaricaBacheca();
    }

    /**
     *Caricamento in finestra di annuncio in base all'utente
     * @param utente Utente per il filtraggio
     */
    static void ricercaAnnuncio(Utente utente){
        caricaAnnunci(Bacheca.filtraProprietario(Bacheca.getBacheca(), utente));
    }

    /**
     * Caricamento in finestra di annuncio in base all'utente e alle chiavi
     * @param chiavi Stringa di chiavi con con separatore
     * @param utente Utente per il filtraggio
     */
    static void ricercaAnnuncio(String chiavi, Utente utente){
        caricaAnnunci(Bacheca.ricerca(Bacheca.filtraProprietario(Bacheca.getBacheca(), utente), Annuncio.chiaviToLista(chiavi)));
    }

    /**
     * Caricamento in finestra di annuncio in base all'utente alle chiavi e al tipo di annuncio
     * @param chiavi Stringa di chiavi con con separatore
     * @param tipo Tipo dell'annuncio, Acquisto/Vendita
     * @param utente Utente per il filtraggio
     */

    static void ricercaAnnuncio(String chiavi, Class<? extends Annuncio> tipo, Utente utente){
        caricaAnnunci(Bacheca.ricerca(Bacheca.filtraTipo(Bacheca.filtraProprietario(Bacheca.getBacheca(), utente), tipo), Annuncio.chiaviToLista(chiavi)));
    }

    /**
     * Caricamento in finestra di annuncio in base all'utente e al tipo di annuncio
     * @param tipo Tipo dell'annuncio, Acquisto/Vendita
     * @param utente Utente per il filtraggio
     */
    static void ricercaAnnuncio(Class<? extends Annuncio> tipo, Utente utente){
        caricaAnnunci(Bacheca.filtraProprietario(Bacheca.filtraTipo(Bacheca.getBacheca(), tipo), utente));
    }

    /**
     * Ricerca l'annuncio in base al tipo Acquisto/Vendita e alla chiave
     * @param chiavi Stringa di chiavi con con separatore
     * @param tipo Tipo dell'annuncio, Acquisto/Vendita
     */
    static void ricercaAnnuncio(String chiavi, Class<? extends Annuncio> tipo){
        caricaAnnunci(Bacheca.filtraTipo(Bacheca.ricerca(Bacheca.getBacheca(), Annuncio.chiaviToLista(chiavi)), tipo));
    }

    /**
     * Ricerca l'annuncio in base al tipo Acquisto/Vendita Overload del metodo precedente
     * @param tipo Tipo dell'annuncio, Acquisto/Vendita
     */
    static void ricercaAnnuncio(Class<? extends Annuncio> tipo){
        caricaAnnunci(Bacheca.filtraTipo(Bacheca.getBacheca(), tipo));
    }

    /**
     * Caricamento in finestra di annuncio in base alle chiavi
     * @param chiavi Stringa di chiavi con con separatore
     */
    static void ricercaAnnuncio(String chiavi){
        caricaAnnunci(Bacheca.ricerca(Bacheca.getBacheca(), Annuncio.chiaviToLista(chiavi)));
    }

    /**
     * Rimuove l'annuncio tramite l'id
     * @param IDAnnuncio ID dell'annuncio
     * @throws RemoveException Eccezione specifica per errore nella rimozione dell'annuncio
     */
    static void rimuoviAnnuncio(String IDAnnuncio) throws RemoveException {
        Bacheca.rimuoviAnnuncio(IDAnnuncio, sessione.getCurrentUser());
        caricaAnnunciModificabili();
    }


    /**
     * Permette il login settando la sessione
     * @param email Email utente
     * @param password Password utente
     * @throws PasswordException Eccezione specifica se la password è errata
     * @throws UserListException Eccezione specifica per la lista utenti
     */
    static void faiLogin(String email, String password) throws PasswordException, UserListException {
        sessione.setCurrentUser(Utente.loginUtente(email, password));
    }

    /**
     * Permette la registrazione dell'utente
     * @param email Email utente
     * @param password Password utente
     * @param nome Nome dell'utente
     * @throws UserListException Eccezione specifica per la lista utenti
     */

    static void faiRegistrazione(String email,String password,String nome) throws UserListException, UserCreationException {
            Utente nuovo = Utente.creaUtente(password, email, nome);
            sessione.setCurrentUser(nuovo);
            store(); // Salvataggio
    }

    /**
     * Fa il logout e salva i dati su File
     */
    static void faiLogout(){
        store();
    }

    /**
     * Ritorna l'utente della sessione
     * @return ottiene l'utente corrente
     */
    static Utente getUtenteCorrente(){
        return sessione.getCurrentUser();
    }
}
