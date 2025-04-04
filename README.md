# Relazione: Bacheca annunci

Progetto realizzato da:

- CristianCrazyIT

- SimoneIlNavy

## Introduzione

É stato progettato e implementato un sistema per la gestione di una **Bacheca di annunci**, dove gli utenti possono pubblicare, visualizzare e modificare gli annunci. Per realizzare il progetto è stato utilizzata la versione **JDK 21**.

## Struttura progetto

Per garantire una buona struttura e per evitare conflitti di nomi, soprattutto  nel caso in cui venga utilizzato per realizzare altri applicativi Java, il progetto è stato costruito attorno al package `sc.pdg.hnk`.

La logica del programma e le interfacce utente sono state ulteriormente separate nei seguenti package:

- `app`: contiene le classi che implementano la logica di base del programma, e comprende anche la classe `Main`.

    - `annuncio`: contiene le classi che implementano la logica degli annunci, e comprende la classe astratta `Annuncio` con le sue relative specializzazioni: `Acquisto` e `Vendita`. Inoltre, il package contiene la classe eccezione `AnnuncioException`, che viene lanciata quando vi è un problema legato alla creazione degli annunci.

    - `bacheca`: contiene le classi che gestiscono la logica della bacheca comprende la classe principale`Bacheca` e le relative eccezione come`BachecaException` l'eccezione generale padre e le relative eccezioni che ne derivano:`BachecaIOException` che viene lanciata quando avviene un problema sulla lettura e sulla scrittura della bacheca su file,`BachecaNotFoundException` che viene lanciata quando la bacheca non viene trovata e `RemoveException` quando non risulta possibile rimuovere l'annuncio dalla bacheca
    
    - `sessione`: contiene la classe `Sessione` che permette di gestire le informazioni relative alla singola sessione dell'utente.

    - `utente`: contiene le classi che implementano la logica dell'utente. Comprende la classe `Utente` con le sue relative classi eccezione: `UserException` eccezione di base da cui derivano `PasswordException` lanciata nel caso in cui l'utente sbagli la password, `UserListException` lanciata nel caso in cui l'utente non esiste nella userlist o nel caso in cui esiste già, `UserCreationException` nel caso in cui l'utente non specifica i parametri o i parametri inseriti non sono corretti. 

- `cli`: contiene le classi che implementano l'interfaccia a linea di comando.

- `gui`: contiene le classi che implementano l'interfaccia grafica in Swing.

### Classi

 - `Acquisto`
    - Classe figlia della classe astratta Annuncio che restituisce un oggetto Acquisto contenente tutti i dati dell'annuncio di acquisto

- `Vendita`
    - Classe figlia della classe astratta Annuncio che restituisce un oggetto Vendita contenente tutti i dati dell'annuncio di vendita

- `Annuncio`
    
    - Classe astratta che contiene tutti i valori in comune delle classi `Acquisto` e `Vendita` quali:

        - nome 
    
        - descrizione
    
        - proprietario 
    
        - Parole Chiave
    
- `Bacheca`
    
    - Bacheca contiene la lista principale di annunci e tutti i metodi che consentono di gestire le funzioni della bacheca.

- `Sessione`
    
    - Sessione contiene i dati "Utente" relativi all'utilizzantore del programma come:
        - utente (oggetto di tipo Utente che contiene i dati relativi all'utilizzatore corrente dell programma)
        - fileBackup (oggetto di tipo path che contiene il percorso del file dove vengono salvati i dati)

- `Utente`

    - Utente rappresenta un utente all'interno dell'applicazione. Offre metodi che permettono di creare ed effettuare operazioni con l'utente, oltre a contenere la lista degli utenti.


### Classi CLI

La parte di CLI del progetto è composta solamente dalla classe `ComandiCLI` che 
implementa la logica del programma permettendo all'utente di effettuare tutte le operazioni sulla bacheca tramite l'implementazione di vari menu, realizzati e visualizzati sulla linea di comando.

### Classi GUI

La parte di GUI del progetto è composta da diverse classi, ciascuna è responsabile di una finestra o di una sezione dell'applicazione, e le comunicazioni tra queste classi permettono all'utente di effettuare tutte le operazioni sulla bacheca. Di seguito sono riportate le descrizioni di ciascuna classe.

- `LoginFrame`: gestisce la finestra di login. Lanciata all'avvio dell'applicazione, la finestra permette all'utente di inserire le proprie credenziali di accesso.

- `RegistrazioneFrame`: gestisce la finestra di registrazione di un nuovo utente. Permette all'utente di registrarsi inserendo username, password e indirizzo email.

- `BachecaFrame`: gestisce la finestra principale della bacheca.

- `AggiuntaFrame`: gestisce la finestra che permette agli utenti di aggiungere un nuovo annuncio di acquisto o di vendita alla bacheca.

- `RicercaFrame`: gestisce la finestra che permette agli utenti di effettuare una ricerca sugli annunci presenti nella bacheca. É possibile effettuare operazioni di filtrazione per **tipologia di annuncio** e permette di visualizzare gli annunci dell'utente che ha effettuato il login. 

- `AnnuncioPanel`: è un pannello che visualizza un singolo annuncio. Ogni annuncio pubblicato viene rappresentato da un'istanza di questa classe, che mostra le informazioni rilevanti e permette di effettuare alcune operazioni, come l'aggiunta di una parola chiave o l'eliminazione.

- `ComandiGUI`: classe che richiama la logica del programma e fornisce una serie di metodi statici che vengono successivamente richiamati dalle classi di questo package. Questa classe permette di collegare la logica del programma all'interfaccia grafica.  

> Alcune parti delle classi che implementano l'interfaccia grafica in Swing sono state inzialmente realizzate utilizzando un editor WYSIWYG (JFormDesigner), successivamente è stato effettuato il binding tra l'interfaccia grafica e la logica del programma tramite la scrittura di classi appositamente studiate per l'interazione delle stesse.

## Parametri

Quando il programma viene avviato senza alcun parametro aggiuntivo, viene avviata l'interfaccia GUI e gli annunci vengono caricati dal file `bacheca.dat`, e se non presente viene creato.

Altrimenti è possibile specificare i seguenti parametri per cambiare il comportamento del programma:

|   Parametro     | Descrizione                                     |
|-----------------|-------------------------------------------------|
| `-cli`          | avvia programma su CLI                          |
| `-file:"(path)"`| carica il file bacheca specificato nel path     |