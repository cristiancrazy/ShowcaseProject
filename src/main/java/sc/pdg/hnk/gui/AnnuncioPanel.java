package sc.pdg.hnk.gui;

import java.awt.event.*;
import sc.pdg.hnk.app.annuncio.Acquisto;
import sc.pdg.hnk.app.annuncio.Annuncio;
import sc.pdg.hnk.app.annuncio.Vendita;
import sc.pdg.hnk.app.bacheca.RemoveException;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;

/**
 * Classe per il frame annuncio
 */
public class AnnuncioPanel extends JPanel {
    private final Annuncio riferimento;

    /**
     * Costruttore che incapsula gli annunci
     * @param annuncio Annuncio
     */
    public AnnuncioPanel(Annuncio annuncio) {
        initComponents();
        // Parsing dell'annuncio
        this.riferimento = annuncio;
        descrizioneField.setText(annuncio.getDescrizione());
        proprietarioLabel.setText("Di: " + annuncio.getProprietario().getNome());
        if(annuncio instanceof Vendita){
            nomeLabel.setText("Vendita: " + annuncio.getNome());
            statoLabel.setText(((Vendita) annuncio).getStato());
            scadenzaLabel.setText(((Vendita) annuncio).getScadenza().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            prezzoLabel.setText(((Vendita) annuncio).getPrezzo()+" EUR");
        }else if(annuncio instanceof Acquisto){
            nomeLabel.setText("Ricerca: " + annuncio.getNome());
            statoLabel.setText("");
            scadenzaLabel.setText("");
            prezzoLabel.setText(((Acquisto) annuncio).getMin() + "-" + ((Acquisto) annuncio).getMax() + " EUR");
        }
    }

    /**
     * Se viene abilitata la modifica si rendono visibili  i bottoni per l'eliminazione e aggiunta chiave
     */
    public void abilitaModifica(){
        this.eliminaButton.setVisible(true);
        this.aggiungiChiaveButton.setVisible(true);
    }

    /**
     * Aggiunge le chiavi all'annuncio selezionato
     */
    private void aggiungiChiave(ActionEvent e) {
        String chiave = JOptionPane.showInputDialog(null, "Aggiungi una nuova chiave all'annuncio selezionato", "Nuova chiave", JOptionPane.PLAIN_MESSAGE);
        if(chiave == null){
            // Operazione annullata
            return;
        }

        if(!chiave.isEmpty()){
            riferimento.aggiungiChiave(chiave);
            JOptionPane.showMessageDialog(null, "Chiave: "+chiave+"\naggiunta all'annuncio.", "Parola chiave aggiunta", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "La chiave non è stata aggiunta", "Parola chiave non aggiunta", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *Rimuove l'annuncio se è il proprietario
     */
    private void elimina(ActionEvent e) {
        try{
            ComandiGUI.rimuoviAnnuncio(this.riferimento.getIDAnnuncio());
        }catch(RemoveException ecc){
            JOptionPane.showMessageDialog(null, ecc.getMessage(), "Errore - Accesso negato", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Inizializza i componenti frame
     */
    private void initComponents() {
        nomeLabel = new JLabel();
        wrapperDescrizione = new JScrollPane();
        descrizioneField = new JTextPane();
        proprietarioLabel = new JLabel();
        statoLabel = new JLabel();
        scadenzaLabel = new JLabel();
        prezzoLabel = new JLabel();
        eliminaButton = new JButton();
        aggiungiChiaveButton = new JButton();

        //======== this ========
        setBorder(new LineBorder(Color.BLACK));

        //---- nomeLabel ----
        nomeLabel.setText("Nome");

        //======== wrapperDescrizione ========
        {

            //---- descrizioneField ----
            descrizioneField.setText("Descrizione");
            descrizioneField.setEditable(false);
            wrapperDescrizione.setViewportView(descrizioneField);
        }

        //---- proprietarioLabel ----
        proprietarioLabel.setText("Proprietario");

        //---- statoLabel ----
        statoLabel.setText("Stato");

        //---- scadenzaLabel ----
        scadenzaLabel.setText("Scadenza");

        //---- prezzoLabel ----
        prezzoLabel.setText("Prezzo");
        prezzoLabel.setFont(prezzoLabel.getFont().deriveFont(prezzoLabel.getFont().getSize() + 4f));
        prezzoLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        //---- eliminaButton ----
        eliminaButton.setText("Elimina");
        eliminaButton.setVisible(false);
        eliminaButton.addActionListener(this::elimina);

        //---- aggiungiChiaveButton ----
        aggiungiChiaveButton.setText("Aggiungi chiave");
        aggiungiChiaveButton.setVisible(false);
        aggiungiChiaveButton.addActionListener(this::aggiungiChiave);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(wrapperDescrizione, GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(nomeLabel)
                            .addGap(0, 354, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(proprietarioLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(statoLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                            .addComponent(scadenzaLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(eliminaButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(aggiungiChiaveButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
                            .addComponent(prezzoLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(nomeLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(wrapperDescrizione, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(proprietarioLabel)
                        .addComponent(statoLabel)
                        .addComponent(scadenzaLabel))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(prezzoLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(eliminaButton)
                                .addComponent(aggiungiChiaveButton))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
        );
    }

    private JLabel nomeLabel;
    private JScrollPane wrapperDescrizione;
    private JTextPane descrizioneField;
    private JLabel proprietarioLabel;
    private JLabel statoLabel;
    private JLabel scadenzaLabel;
    private JLabel prezzoLabel;
    private JButton eliminaButton;
    private JButton aggiungiChiaveButton;
}
