package sc.pdg.hnk.app;

import sc.pdg.hnk.app.annuncio.Annuncio;
import sc.pdg.hnk.app.annuncio.Vendita;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Bacheca implements Serializable, Iterable<Annuncio> {
    private static ArrayList<Annuncio> bacheca = new ArrayList<>();

    // Rimuove gli annunci di vendita scaduti
    public static void pulisciBacheca(){
        for(Annuncio annuncio : bacheca){
            if(annuncio instanceof Vendita){
                if (((Vendita) annuncio).scaduto()){
                    bacheca.remove(annuncio);
                }
            }
        }
    }

    // Rimuovi annuncio con ID
    private static boolean rimuoviAnnuncio(String IDAnnuncio){
        for(Annuncio a : bacheca){
            if (Objects.equals(a.getIDAnnuncio(), IDAnnuncio)){
                bacheca.remove(a);
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Annuncio> iterator() {
        return new IteratoreBacheca();
    }

    private static class IteratoreBacheca implements Iterator<Annuncio>{
        private int indice = 0;

        @Override
        public boolean hasNext() {
            return indice < bacheca.size();
        }

        @Override
        public Annuncio next() {
            Annuncio attuale = bacheca.get(indice);
            ++indice;
            return attuale;
        }
    }
}
