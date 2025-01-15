package sc.pdg.hnk.gui;

import sc.pdg.hnk.app.annuncio.Annuncio;
import sc.pdg.hnk.app.bacheca.Bacheca;
import sc.pdg.hnk.app.bacheca.BachecaIOException;
import sc.pdg.hnk.app.bacheca.BachecaNotFoundException;
import sc.pdg.hnk.app.sessione.Sessione;
import sc.pdg.hnk.app.utente.PasswordException;
import sc.pdg.hnk.app.utente.UserException;
import sc.pdg.hnk.app.utente.UserListException;
import sc.pdg.hnk.app.utente.Utente;

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
        caricaAnnunci(Bacheca.getBacheca());
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
