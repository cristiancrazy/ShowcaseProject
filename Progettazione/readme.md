# Progetto paradigmi

## Struttura a classi del progetto

Il progetto è costituito dalle seguenti classi suddivise nei rispettivi package:

- **Bacheca**
  - Contiene una collezione di annunci
  - Deve mettere a disposizione un iteratore
  - Deve contenere un metodo che permette la pulizia della bacheca dagli annunci scaduti
- **Utente**
  - Identificato dall'email
  - Ha un nome

- **Annuncio**
  - La superclasse annuncio possiede i seguenti parametri: id, nome, descrizione, parole chiave (stringa con separatore), e l'utente che inserisce l'annuncio, condizione del prodotto.
    - **Vendita**
      - Possiede una **data di scadenza**
    - **Acquisto**
      - Price range
      
Un utente può:
- inserire nuova parola chiave
- inserire annuncio in bacheca (tipologia, nome, prezzo, parole chiave, data di scadenza (per vendita))
  - se è *Acquisto* una volta inserito, viene ritornata una lista di annunci di vendita che possiedono le parole chiave (intersecazione)
- rimuovere annuncio (lo può fare solo l'utente che lo ha inserito)
- <mark>rimuovere annuncio da id</mark>
- Ricerca articoli per parole chiave -> ritorna lista di parole chiave che intersecano quelle specificate dall'utente
- Chiedere di ripulire la bacheca dagli annunci scaduti
     