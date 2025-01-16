package sc.pdg.hnk.gui;

import sc.pdg.hnk.app.annuncio.Acquisto;
import sc.pdg.hnk.app.annuncio.Annuncio;
import sc.pdg.hnk.app.annuncio.Vendita;
import sc.pdg.hnk.app.bacheca.Bacheca;
import sc.pdg.hnk.app.bacheca.BachecaIOException;
import sc.pdg.hnk.app.bacheca.BachecaNotFoundException;
import sc.pdg.hnk.app.sessione.Sessione;
import sc.pdg.hnk.app.utente.PasswordException;
import sc.pdg.hnk.app.utente.UserException;
import sc.pdg.hnk.app.utente.UserListException;
import sc.pdg.hnk.app.utente.Utente;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ComandiGUI {
    private static final Sessione sessione = new Sessione();
    private static BachecaFrame bacheca = new BachecaFrame();

    public static void lanciaGUI(){
        load();
        new LoginFrame().setVisible(true);
    }

    ComandiGUI(){

    }

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

    static void lanciaBacheca(){
        bacheca = new BachecaFrame();
        bacheca.setVisible(true);
        caricaAnnunci(Bacheca.getBacheca());
    }

    static void ricaricaBacheca(){
        caricaAnnunci(Bacheca.getBacheca());
    }

    static void pulisciBacheca(){
        Bacheca.pulisciBacheca();
        ricaricaBacheca();
        JOptionPane.showMessageDialog(null, "Sono stati cancellati tutti gli annunci scaduti.", "Pulizia completata", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void caricaAnnunci(List<Annuncio> annunci){
        bacheca.getPanelAnnunci().removeAll();

        for(Annuncio a : annunci){
            bacheca.getPanelAnnunci().add(new AnnuncioPanel(a));
        }
        bacheca.repaint();
        bacheca.revalidate();
    }

    static void aggiungiAnnuncio(Annuncio annuncio){
        Bacheca.aggiungiAnnuncio(annuncio);
        ricaricaBacheca();
    }

    // Filtra ed effettua ricerca
    static void ricercaAnnuncio(String chiavi, Class<? extends Annuncio> tipo){
        if(tipo == null){
            caricaAnnunci(Bacheca.ricerca(Bacheca.getBacheca(), Annuncio.chiaviToLista(chiavi)));
        }else{
            caricaAnnunci(Bacheca.filtraTipo(Bacheca.ricerca(Bacheca.getBacheca(), Annuncio.chiaviToLista(chiavi)), tipo));
        }
    }

    static void ricercaAnnuncio(Class<? extends Annuncio> tipo){
        if(tipo == null){
            caricaAnnunci(Bacheca.getBacheca());
        }else{
            caricaAnnunci(Bacheca.filtraTipo(Bacheca.getBacheca(), tipo));
        }
    }


    // Filtra gli annunci dell'utente
    static void ricercaAnnunciUtente(Class<? extends Annuncio> tipo,  Utente utente){
        if(tipo == null){
            caricaAnnunci(Bacheca.getBacheca().stream().filter(a -> a.isProprietario(utente)).toList());
        }else{
            caricaAnnunci(Bacheca.filtraTipo(Bacheca.getBacheca().stream().filter(a -> a.isProprietario(utente)).toList(), tipo));
        }
    }

    // Filtra gli annunci dell'utente
    static void ricercaAnnunciUtente(String chiavi, Class<? extends Annuncio> tipo,  Utente utente){
        if(tipo == null){
            caricaAnnunci(Bacheca.ricerca(Bacheca.getBacheca(), Annuncio.chiaviToLista(chiavi)).stream().filter(a -> a.isProprietario(utente)).toList());
        }else{
            caricaAnnunci(Bacheca.filtraTipo(Bacheca.ricerca(Bacheca.getBacheca(), Annuncio.chiaviToLista(chiavi)).stream().filter(a -> a.isProprietario(utente)).toList(), tipo));
        }
    }



    static void faiLogin(String email, String password) throws PasswordException, UserListException {
        sessione.setCurrentUser(Utente.loginUtente(email, password));
    }

    static void faiRegistrazione(String email,String password,String nome) throws UserListException{
            Utente nuovo = Utente.creaUtente(password, email, nome);
            sessione.setCurrentUser(nuovo);
            store(); // Salvataggio
    }

    static void faiLogout(){
        store();
    }

    static Utente getUtenteCorrente(){
        return sessione.getCurrentUser();
    }
}
