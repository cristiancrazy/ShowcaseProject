package sc.pdg.hnk.app.bacheca;

import sc.pdg.hnk.app.annuncio.Acquisto;
import sc.pdg.hnk.app.annuncio.Annuncio;
import sc.pdg.hnk.app.annuncio.Vendita;
import sc.pdg.hnk.app.utente.Utente;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

/**
 * Bacheca contiene la lista principale di annunci e tutti i metodi
 * che consentono di gestire le funzioni della bacheca.
 * @author Cristian, Simone
 */
public class Bacheca implements Iterable<Annuncio> {

    private static List<Annuncio> bacheca = new ArrayList<>();

    /**
     * Funzione che permette di aggiungere un annuncio alla bacheca
     * @param annuncio Oggetto di tipo Annuncio, l'annuncio da inserire
     * @return nel caso l'annuncio sia di tipo "Acquisto" viene ritornata la lista di annunci che intersecano
     * le parole chiave dell'annuncio inserito, se non dovessero esserci la lista sarà vuota, se invece il tipo
     * di annuncio dovesse essere "Vendita" allora viene ritornato null
     */
    public static List<Annuncio> aggiungiAnnuncio(Annuncio annuncio){
        bacheca.add(annuncio);
        if(annuncio instanceof Acquisto){
            return ricerca(filtraTipo(bacheca, Vendita.class), annuncio.getChiavi());
        }
        return null;
    }

    /**
     * Rimuove tutti gli annunci scaduti
     */
    public static void pulisciBacheca(){
        for(Annuncio annuncio : bacheca){
            if(annuncio instanceof Vendita){
                if (((Vendita) annuncio).scaduto()){
                    bacheca.remove(annuncio);
                }
            }
        }
    }

    /**
     * Filtraggio annunci per tipo
     * @param annunci Lista di annunci
     * @param tipo tipo sul quale effettuare il filtraggio, solo figli di annuncio
     * @return Lista di annunci filtrata
     */
    public static List<Annuncio> filtraTipo(List<Annuncio> annunci, Class<? extends Annuncio> tipo){
        return annunci.stream().filter(tipo::isInstance).toList();
    }

    /**
     * Ricerca degli annunci contenenti tutte le chiavi
     * @param annunci Lista di annunci
     * @param chiavi Set di chiavi per la ricerca
     * @return Lista di annunci risultante
     */
    public static List<Annuncio> ricerca(List<Annuncio> annunci, Set<String> chiavi){
        List<Annuncio> trovati = new ArrayList<>();

        for (Annuncio a : annunci){
            if(new HashSet<>(a.getChiavi().stream().map(String::toLowerCase).toList()).containsAll(chiavi.stream().map(String::toLowerCase).toList())){
                trovati.add(a);
            }
        }
        return trovati;
    }

    /**
     * Rimuove un annuncio dell'utente tramite ID
     * @param IDAnnuncio Stringa contenente l'id dell'annuncio
     * @param proprietario Utente che sta tentando di rimuovere l'annuncio
     * @return true quando viene rimosso, false quando l'annuncio non esiste
     * @throws RemoveException eccezione generata quando l'utente non è il proprietario dell'annuncio
     */
    public static boolean rimuoviAnnuncio(String IDAnnuncio, Utente proprietario) throws RemoveException {

        for(Annuncio a : bacheca){
            if (Objects.equals(a.getIDAnnuncio(), IDAnnuncio)){
                if(a.isProprietario(proprietario)) {
                    bacheca.remove(a);
                    return true;
                }else{
                    throw new RemoveException("L'utente non è il proprietario dell'annuncio");
                }
            }
        }
        return false;
    }

    /**
     * Funzione che serializza su file la bacheca e la lista utenti
     * @param file path del file dove viene effettuato il salvataggio
     * @throws BachecaIOException Eccezione generata da un errore di IO
     */
    public static void salvataggio(Path file) throws BachecaIOException{
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
            out.writeObject(bacheca);
            out.writeObject(Utente.getUserList());
        } catch (FileNotFoundException e) {
            throw new BachecaIOException("Errore IO - esportazione bacheca: file non trovato.");
        } catch (IOException e) {
            throw new BachecaIOException("Errore IO - esportazione bacheca.");
        }
    }

    /**
     * Funzione che ricarica da file la bacheca e la lista utenti
     * @param file path del file dove vengono caricati i dati
     * @throws BachecaIOException Eccezione generata da un errore di IO
     * @throws BachecaNotFoundException Eccezione generata dalla mancanza del file nel path specificato
     * @throws BachecaParsingException Eccezione generata da un errore di lettura del file
     */
    @SuppressWarnings("unchecked")
    public static void recupero(Path file) throws BachecaIOException, BachecaNotFoundException, BachecaParsingException{
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.toFile()))) {
            bacheca = (List<Annuncio>) in.readObject();
            Utente.setUserList((HashMap<String, Utente>) in.readObject());
        } catch (FileNotFoundException e) {
            throw new BachecaNotFoundException("Errore IO - importazione bacheca/utenti: file non trovato.");
        } catch (IOException e) {
            throw new BachecaIOException("Errore IO - importazione bacheca/utenti.");
        } catch (ClassNotFoundException e) {
            throw new BachecaParsingException("Errore importazione bacheca/utenti.");
        }
    }

    /**
     * Restituisce un iteratore della bacheca
     * @return Iteratore della bacheca
     */
    @Override
    public Iterator<Annuncio> iterator() {
        return new IteratoreBacheca();
    }

    /**
     * Classe per l'iteratore della bacheca
     */
    private static class IteratoreBacheca implements Iterator<Annuncio>{
        private int indice = 0;

        /**
         * Controllo per il valore successivo
         * @return ritorna l'indice se minore della grandezza di bacheca
         */
        @Override
        public boolean hasNext() {
            return this.indice < bacheca.size();
        }

        /**
         * Ritorna l'annuncio successivo
         * @return Annuncio successivo
         */
        @Override
        public Annuncio next() {
            Annuncio attuale = bacheca.get(this.indice);
            ++this.indice;
            return attuale;
        }
    }

    /**
     * Ottenimento della bacheca globale degli annunci
     * @return restituisce una lista con tutti gli annunci della bacheca
     */
    public static List<Annuncio> getBacheca() {
        return bacheca;
    }
}
