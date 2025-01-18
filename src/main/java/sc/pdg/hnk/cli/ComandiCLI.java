package sc.pdg.hnk.cli;

import sc.pdg.hnk.app.annuncio.Acquisto;
import sc.pdg.hnk.app.annuncio.Annuncio;
import sc.pdg.hnk.app.annuncio.AnnuncioException;
import sc.pdg.hnk.app.annuncio.Vendita;
import sc.pdg.hnk.app.bacheca.Bacheca;
import sc.pdg.hnk.app.bacheca.BachecaIOException;
import sc.pdg.hnk.app.bacheca.BachecaNotFoundException;
import sc.pdg.hnk.app.bacheca.RemoveException;
import sc.pdg.hnk.app.sessione.Sessione;
import sc.pdg.hnk.app.utente.UserCreationException;
import sc.pdg.hnk.app.utente.UserException;
import sc.pdg.hnk.app.utente.UserListException;
import sc.pdg.hnk.app.utente.Utente;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ComandiCLI {

    private final Sessione sessione = new Sessione();
    private final Scanner scanner = new Scanner(System.in);

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

    /**
     * Costruttore della classe
     */
    public ComandiCLI(){
        load(); /* Caricamento da file */
        mostraMenuUtente(); /* Avvia menù utente */
    }

    /**
     * Costruttore della classe
     * @param fileSalvataggio path del file di salvataggio/caricamento bacheca
     */
    public ComandiCLI(Path fileSalvataggio){
        sessione.setFileBackup(fileSalvataggio);
        load(); /* Caricamento da file */
        mostraMenuUtente(); /* Avvia menù utente */
    }

    /**
     * Pulisce lo schermo dai caratteri delle precedenti azioni
     */
    private void pulisciSchermo(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    /**
     *  Chiede conferma all'utente prima di proseguire
     */
    private void chiediConferma(){
        System.out.println("Premere INVIO per continuare...");
        scanner.nextLine();
    }

    /**
     * Conversioni stringhe in numeri interi.
     * Se fallisce restituisce 0.
     */
    private int parseIntegerOrDefault(String input){
        int out;
        try{
            out = Integer.parseInt(input);
        }catch (Exception e){
            out = 0;
        }
        return out;
    }

    /**
     * Conversioni stringhe in numeri con virgola
     * Se fallisce restituisce 0.0
     */
    private double parseDoubleOrDefault(String input){
        double out;
        try{
            out = Double.parseDouble(input);
        }catch (Exception e){
            out = 0;
        }
        return out;
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
            pulisciSchermo();
            mostraMenuPrincipale();
        }catch(UserException e){
            // Login non effettuato
            System.out.println(e.getMessage());
            chiediConferma();
            pulisciSchermo();
            faiLogin(); // Riprova
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
        }else{
            pulisciSchermo();
            mostraMenuPrincipale();
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
        System.out.print(": ");

        while(!ok) {

            switch (scanner.nextLine()){
                case "1" ->{
                    pulisciSchermo();
                    mostraMenuAnnunci();
                    mostraMenuPrincipale();
                    ok = true;
                }

                case "2" ->{
                    pulisciSchermo();
                    inserimentoAnnuncio();
                    mostraMenuPrincipale();
                    ok = true;
                }
                case "3" ->{
                    pulisciSchermo();
                    Bacheca.pulisciBacheca();
                    System.out.println("Bacheca pulita correttamente");
                    chiediConferma();
                    mostraMenuPrincipale();
                    ok = true;
                }
                case "4" ->{
                    pulisciSchermo();
                    store();
                    chiediConferma();
                    mostraMenuPrincipale();
                    ok = true;
                }
                case "5" ->{
                    ok = true;
                    pulisciSchermo();
                    faiLogout();
                }

            }

        }
    }

    /**
     * Mostra il MenuUtente Permettendo la selezione delle relative funzionalità
     */
    private void mostraMenuAnnunci(){
        boolean ok = false;

        System.out.println("[Bacheca - Menù annunci]");
        System.out.println("1\tVisualizza tutti");
        System.out.println("2\tFiltra per annunci di vendita");
        System.out.println("3\tFiltra per annunci di ricerca");
        System.out.println("4\tCerca annuncio");
        System.out.println("5\tMiei annunci");
        System.out.println("6\tRimuovi annunci");
        System.out.println("7\tAggiungi parola chiave");
        System.out.println("8\tTorna indietro");
        System.out.println(": ");

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
                    System.out.print(": ");

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

                case "7" -> {
                    ok = true;
                    aggiungiChiave();
                }

                case "8" -> ok = true;

                default -> System.out.print(": ");
            }
        }
        chiediConferma();
        pulisciSchermo();
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

    /**
     * Questo metodo permette l'inserimento di un nuovo annuncio in bacheca
     */
    private void inserimentoAnnuncio(){
        boolean ok = false;
        boolean ook = false;
        String nome,descrizione,chiavi;
        double prezzo,max,min;
        LocalDate data;

        pulisciSchermo();

        System.out.println("[Inserimento Nuovo Annuncio - Benvenuto]");
        System.out.println("Effettuare una selezione valida:");
        System.out.println("1\tCrea un annuncio di vendita");
        System.out.println("2\tCrea un annuncio di ricerca");
        System.out.println("3\tEsci");
        System.out.print(": ");

        while(!ok) {
            switch (scanner.nextLine()) {
                case "1" -> {
                    Vendita.Condizioni condizione = Vendita.Condizioni.NUOVO;

                    //Nome
                    System.out.print("Nome annuncio: ");
                    nome = scanner.nextLine();

                    //Descrizione
                    System.out.print("Descrizione: ");
                    descrizione = scanner.nextLine();

                    //Prezzo
                    System.out.print("Prezzo: ");
                    prezzo = parseDoubleOrDefault(scanner.nextLine());

                    //Stato
                    System.out.println("inserire lo stato dell'oggetto");
                    System.out.println("1\tNuovo");
                    System.out.println("2\tCome Nuovo");
                    System.out.println("3\tUsato");
                    System.out.println("4\tMolto Usato");
                    System.out.println("5\tNon Funzionante");
                    System.out.print(": ");
                    while(!ook) {
                        switch (scanner.nextLine()){
                            case "1" -> ook = true;
                            case "2" ->{
                                ook = true;
                                condizione = Vendita.Condizioni.COME_NUOVO;
                            }
                            case  "3" -> {
                                ook = true;
                                condizione = Vendita.Condizioni.USATO;
                            }
                            case  "4" -> {
                                ook = true;
                                condizione = Vendita.Condizioni.MOLTO_USATO;
                            }
                            case  "5" -> {
                                ook = true;
                                condizione = Vendita.Condizioni.NON_FUNZIONANTE;

                            }
                            default -> {
                                System.out.println("Selezione non valida");
                                System.out.print(": ");
                            }
                        }

                    }

                    //chiavi
                    System.out.print("Parole chiave (separate da ','): ");
                    chiavi = scanner.nextLine();

                    //data scadenza
                    System.out.print("imposta la data di scadenza (dd-mm-yyyy):");

                    try{
                        data = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    }catch (DateTimeParseException e){
                        System.out.println("Formato non valido");
                        break;
                    }


                    try{
                        Bacheca.aggiungiAnnuncio(new Vendita(nome, descrizione, sessione.getCurrentUser(), chiavi, prezzo, data, condizione));
                        System.out.println("Annuncio inserito correttamente");
                    }catch (AnnuncioException ecc){
                        System.out.println("Errore: "+ecc.getMessage());
                    }

                    ok = true;
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        mostraMenuPrincipale();
                    }
                }

                case "2" -> {
                    // nome annuncio
                    System.out.print("Nome annuncio: ");
                    nome = scanner.nextLine();

                    // descrizione annuncio
                    System.out.println("Descrizione: ");
                    descrizione = scanner.nextLine();

                    // inserimento chiavi sotto forma di stringa con separatore
                    System.out.print("Parole chiave (separate da ','): ");
                    chiavi = scanner.nextLine();

                    //inserimento prezzo massimo
                    System.out.print("Budget massimo: ");
                    max = parseDoubleOrDefault(scanner.nextLine());

                    //inserimento prezzo minimo
                    System.out.print("Budget minimo: ");
                    min = parseDoubleOrDefault(scanner.nextLine());

                    // Inserimento annuncio di ricerca e ottenimento correlati
                    List<Annuncio> correlati = new ArrayList<>();

                    try{
                        correlati = Bacheca.aggiungiAnnuncio(new Acquisto(nome, descrizione, sessione.getCurrentUser(), chiavi, max, min));
                        System.out.println("Annuncio inserito correttamente");
                    }catch (AnnuncioException ecc) {
                        System.out.println("Errore: " + ecc.getMessage());
                    }

                    ok = true;
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        mostraMenuUtente();
                    }
                    pulisciSchermo();
                    System.out.println("[Bacheca - Annunci di vendita correlati]");
                    Objects.requireNonNullElse(correlati, new ArrayList<>()).forEach(System.out::println);
                    System.out.println("Premi invio per continuare");
                    scanner.nextLine();
                    pulisciSchermo();
                    mostraMenuPrincipale();
                }


                case "3" -> {
                    pulisciSchermo();
                    ok = true;
                }

                default -> {
                    System.out.println("Selezione non valida.");
                    System.out.print(": ");
                }

            }
        }
        chiediConferma();
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
            chiediConferma();
            mostraMenuPrincipale();
        }catch (UserListException | UserCreationException e){
            System.out.println(e.getMessage());
            registraUtente();
        }
    }

    /**
     * Aggiunge una parola chiave dalla lista degli annunci dell'utente
     */
    private void aggiungiChiave(){
        /* Visualizza i miei annunci */
        int selezionato;
        HashMap<Integer, Annuncio> miei = new HashMap<>();
        int index = 1;
        boolean ok = false;

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
            System.out.printf("Seleziona annuncio da modificare (1-%d): ", index-1);
            selezionato = parseIntegerOrDefault(scanner.nextLine());
            try {
                System.out.print("Nuova parola chiave: ");
                Objects.requireNonNull(miei.get(selezionato)).aggiungiChiave(scanner.nextLine());
                ok = true;
            } catch (NullPointerException e){
                System.out.println("Selezione annuncio non valida.");
            }
        }

        chiediConferma();
        pulisciSchermo();
    }

    /**
     * Permette la eliminazione di un annuncio da bacheca se di proprietà dell'utente
     */
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
            System.out.printf("Seleziona annuncio da eliminare (1-%d): ", index-1);
            selezionato = parseIntegerOrDefault(scanner.nextLine());
            try {
                if(Bacheca.rimuoviAnnuncio(Objects.requireNonNull(miei.get(selezionato).getIDAnnuncio()), sessione.getCurrentUser())){
                    System.out.println("Annuncio rimosso");
                }else{
                    System.out.println("L'annuncio selezionato non esiste");
                }
                ok = true;
            } catch (RemoveException e) {
                System.out.println(e.getMessage());
                ok = true;
            } catch (NullPointerException e){
                System.out.println("Selezione non valida.");
            }
        }
        chiediConferma();
        pulisciSchermo();
    }
}
