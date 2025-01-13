package sc.pdg.hnk.cli;

import sc.pdg.hnk.app.annuncio.Acquisto;
import sc.pdg.hnk.app.annuncio.Annuncio;
import sc.pdg.hnk.app.annuncio.Vendita;
import sc.pdg.hnk.app.bacheca.Bacheca;
import sc.pdg.hnk.app.bacheca.BachecaIOException;
import sc.pdg.hnk.app.bacheca.BachecaNotFoundException;
import sc.pdg.hnk.app.bacheca.RemoveException;
import sc.pdg.hnk.app.sessione.Sessione;
import sc.pdg.hnk.app.utente.UserException;
import sc.pdg.hnk.app.utente.UserListException;
import sc.pdg.hnk.app.utente.Utente;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class ComandiCLI {

    private final Sessione sessione = new Sessione();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Costruttore della classe
     */
    public ComandiCLI(){
        load();
        mostraMenuUtente();
    }

    /**
     *  Caricamento bacheca e lista utenti da file
     */
    private void load(){
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
    private void store(){
        try{
            Bacheca.salvataggio(sessione.getFileBackup());
        }catch (BachecaIOException e){
            System.out.println(e.getMessage());
        }
    }

    private void pulisciSchermo(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Fa il login e valorizza la sessione
     */
    private void faiLogin(){
        String email, password = "";
        char[] passwordChars;

        System.out.println("[Bacheca - Login Utente]");
        System.out.print("Email: ");
        email = scanner.nextLine();
        System.out.print("Password: ");
        passwordChars = System.console().readPassword();

        if(passwordChars != null){
            password = new String(passwordChars);
        }

        try {
            sessione.setCurrentUser(Utente.loginUtente(email, password));
            mostraMenuPrincipale();
        }catch(UserException e){
            // Login non effettuato
            System.out.println(e.getMessage());
            System.out.println();
            faiLogin();
        }
    }

    /**
     * Permette il logout dell'account dal programma
     */
    private void faiLogout(){
        System.out.println("Sei sicuro di voler effettuare il logout?");
        System.out.println("1\t Si");
        System.out.println("2\t No");
        System.out.print(": ");

        if(Objects.equals(scanner.nextLine(), "1")){
            mostraMenuUtente();
        }

    }

    /**
     * Chiude il programma salvando le precedenti informazioni
     */
    private void chiudiProgramma(){
        store();
        System.exit(0);
    }

    /**
     * Menu principale dal quale effettuare le funzioni
     */
    private void mostraMenuPrincipale(){
        boolean ok = false;

        System.out.println("[Bacheca - Menù principale]");
        System.out.println("1\tVisualizza annunci");
        System.out.println("2\tNuovo annuncio");
        System.out.println("3\tPulisci bacheca");
        System.out.println("4\tSalva bacheca");
        System.out.println("5\tLogout");

        while(!ok) {

            switch (scanner.nextLine()){
                case "1" ->{
                    pulisciSchermo();
                    mostraMenuAnnunci();
                }

                case "2" ->{
                    pulisciSchermo();
                    inserimentoAnnuncio();
                }
                case "3" ->{
                    pulisciSchermo();
                    Bacheca.pulisciBacheca();
                    System.out.println("Bacheca pulita correttamente");
                }
                case "4" ->{
                    pulisciSchermo();
                    store();
                }
                case "5" ->{
                    ok = true;
                    pulisciSchermo();
                    faiLogout();
                }

            }

        }
    }


    private void mostraMenuAnnunci(){
        boolean ok = false;

        System.out.println("[Bacheca - Menù annunci]");
        System.out.println("1\tVisualizza tutti");
        System.out.println("2\tFiltra per annunci di vendita");
        System.out.println("3\tFiltra per annunci di ricerca");
        System.out.println("4\tCerca annuncio");
        System.out.println("5\tMiei annunci");
        System.out.println("6\tRimuovi annunci");
        System.out.println("7\tTorna indietro");

        while(!ok){
            switch(scanner.nextLine()){
                case "1" -> {
                    ok = true;
                    for(Annuncio a : new Bacheca()){
                        System.out.println(a);
                    }
                }

                case "2" -> {
                    ok = true;
                    Bacheca.filtraTipo(Bacheca.getBacheca(), Vendita.class).forEach(System.out::println);
                }

                case "3" -> {
                    ok = true;
                    Bacheca.filtraTipo(Bacheca.getBacheca(), Acquisto.class).forEach(System.out::println);
                }

                case "4" -> {
                    ok = true;
                    boolean ook = false;

                    System.out.println("Tipologie annunci:");
                    System.out.println("1\tRicerca");
                    System.out.println("2\tVendita");
                    System.out.println("3\tTutti");
                    System.out.println("4\tTorna indietro");

                    while(!ook){
                        switch(scanner.nextLine()){
                            case "1" -> {
                                ook = true;
                                System.out.print("Parole chiave: ");
                                Bacheca.ricerca(Bacheca.filtraTipo(Bacheca.getBacheca(), Acquisto.class), Annuncio.chiaviToLista(scanner.nextLine())).forEach(System.out::println);
                            }
                            case "2" -> {
                                ook = true;
                                System.out.print("Parole chiave: ");
                                Bacheca.ricerca(Bacheca.filtraTipo(Bacheca.getBacheca(), Vendita.class), Annuncio.chiaviToLista(scanner.nextLine())).forEach(System.out::println);

                            }
                            case "3" -> {
                                ook = true;
                                System.out.print("Parole chiave: ");
                                Bacheca.ricerca(Bacheca.getBacheca(), Annuncio.chiaviToLista(scanner.nextLine())).forEach(System.out::println);
                            }
                            case "4" -> ook = true;
                        }
                    }
                }

                case "5" -> {
                    ok = true;
                    for(Annuncio a : new Bacheca()){
                        if(a.isProprietario(sessione.getCurrentUser())){
                            System.out.println(a);
                        }
                    }
                }

                case "6" -> {
                    ok = true;
                    rimuoviAnnuncio();
                }

                case "7" -> ok = true;

                default -> System.out.print(": ");
            }
        }
    }

    /**
     * Mostra il menu CLI permettendo la selezione delle azioni
     */
    private void mostraMenuUtente(){
        boolean ok = false;

        System.out.println("[Bacheca - Benvenuto]");
        System.out.println("Effettuare una selezione valida:");
        System.out.println("1\tVai al Login");
        System.out.println("2\tCrea nuovo utente");
        System.out.println("3\tEsci");
        System.out.print(": ");

        while(!ok){
            switch(scanner.nextLine()){
                case "1" -> {
                    faiLogin();
                    ok = true;
                }

                case "2" -> {
                    registraUtente();
                    ok = true;
                }

                case "3" -> {
                    ok = true;
                    chiudiProgramma();
                }

                default -> {
                    System.out.println("Selezione non valida.");
                    System.out.print(": ");
                }
            }
        }

    }

    private void inserimentoAnnuncio(){
        boolean ok = false;
        boolean ook = false;

        pulisciSchermo();

        System.out.println("[Inserimento Nuovo Annuncio - Benvenuto]");
        System.out.println("Effettuare una selezione valida:");
        System.out.println("1\tCrea un annuncio di vendita");
        System.out.println("2\tCrea un annuncio di acquisto");
        System.out.println("3\tEsci");
        System.out.print(": ");

        while(!ok) {
            String nome,descrizione,chiavi;
            double prezzo;
            switch (scanner.nextLine()) {
                case "1" -> {
                    Vendita.Condizioni condizione=Vendita.Condizioni.NUOVO;
                    System.out.println("inserire il nome dell'annuncio");
                    nome = scanner.nextLine();
                    System.out.println("inserire la descrizione dell'annuncio");
                    descrizione = scanner.nextLine();
                    System.out.println("inserire il prezzo dell'annuncio");
                    prezzo = scanner.nextDouble();
                    System.out.println("inserire lo stato dell'oggetto");
                    System.out.println("1\tNuovo");
                    System.out.println("2\tCome Nuovo");
                    System.out.println("3\tUsato");
                    System.out.println("4\tMolto Usato");
                    System.out.println("5\tNon Funzionante");
                    while(!ook) {
                        switch (scanner.nextLine()){
                            case "1" -> ook=true;
                            case "2" ->{
                                ook=true;
                                condizione=Vendita.Condizioni.COME_NUOVO;
                            }
                            case  "3" -> {
                                ook=true;
                                condizione=Vendita.Condizioni.USATO;
                            }
                            case  "4" -> {
                                ook=true;
                                condizione=Vendita.Condizioni.MOLTO_USATO;
                            }
                            case  "5" -> {
                                ook=true;
                                condizione=Vendita.Condizioni.NON_FUNZIONANTE;

                            }
                            default -> {
                                System.out.println("Selezione non valida");
                                System.out.print(": ");
                            }
                        }

                    }
                    System.out.println("imposta le parole chiave separate da ,");
                    chiavi=scanner.nextLine();
                    Bacheca.aggiungiAnnuncio(new Vendita(nome,descrizione,sessione.getCurrentUser(),chiavi,prezzo,condizione));
                }

                case "2" -> {


                }

                case "3" -> {

                }

                default -> {
                    System.out.println("Selezione non valida.");
                    System.out.print(": ");
                }

            }
        }
    }

    /**
     * Permette la registrazione di un nuovo utente
     */
    private void registraUtente(){
        String email, nome, password;

        System.out.println("[Bacheca - Registrazione Utente]");
        // Email
        System.out.print("Email: ");
        email = scanner.nextLine();
        // Nome utente
        System.out.print("Nome: ");
        nome = scanner.nextLine();
        // Password
        System.out.print("Password: ");
        password = scanner.nextLine();

        try{
            Utente nuovo = Utente.creaUtente(password, email, nome);
            this.sessione.setCurrentUser(nuovo);
            System.out.println("Utente creato con successo.");
        }catch (UserListException e){
            System.out.println(e.getMessage());
            registraUtente();
        }
    }

    private void rimuoviAnnuncio(){
        boolean ok = false;
        int selezionato;

        /* Visualizza i miei annunci */
        HashMap<Integer, Annuncio> miei = new HashMap<>();
        int index = 1;
        for (Annuncio a : new Bacheca()) {
            if (a.isProprietario(sessione.getCurrentUser())) {
                miei.put(index, a);
                System.out.println(index+"\t"+a);
                index++;
            }
        }

        if(index == 1){
            System.out.println("Nessun annuncio presente.");
            return;
        }

        while(!ok){
            System.out.printf("Seleziona annuncio da eliminare (1-%d): ", index);
            selezionato = scanner.nextInt();
            try {
                if(Bacheca.rimuoviAnnuncio(Objects.requireNonNull(miei.get(selezionato).getIDAnnuncio()), sessione.getCurrentUser())){
                    System.out.println("Annuncio rimosso");
                }else{
                    System.out.println("L'annuncio selezionato non esiste");
                };
                ok = true;
            } catch (RemoveException e) {
                System.out.println(e.getMessage());
                ok = true;
            } catch (NullPointerException e){
                System.out.println("Selezione non valida.");
            }
        }
    }
}
