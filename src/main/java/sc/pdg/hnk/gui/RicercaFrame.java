package sc.pdg.hnk.gui;

import sc.pdg.hnk.app.annuncio.Acquisto;
import sc.pdg.hnk.app.annuncio.Vendita;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * Classe per il frame di ricerca
 */
public class RicercaFrame extends JFrame {
    public RicercaFrame() {
        initComponents();
    }

    /**
     *Metodo per la ricerca dell'annuncio
     */
    private void ricerca(ActionEvent e) {
        boolean vendita = this.venditaCheck.isSelected();
        boolean acquisto = this.acquistoCheck.isSelected();

        if(mieiCheck.isSelected()){
            if(vendita&&acquisto){
                if(chiaviField.getText().isEmpty()){
                    ComandiGUI.ricercaAnnuncio(ComandiGUI.getUtenteCorrente());
                }else{
                    ComandiGUI.ricercaAnnuncio(chiaviField.getText(), ComandiGUI.getUtenteCorrente());
                }
            }else if(vendita){
                if(chiaviField.getText().isEmpty()){
                    ComandiGUI.ricercaAnnuncio(Vendita.class, ComandiGUI.getUtenteCorrente());
                }else{
                    ComandiGUI.ricercaAnnuncio(this.chiaviField.getText(), Vendita.class, ComandiGUI.getUtenteCorrente());
                }
            }else if(acquisto){
                if(chiaviField.getText().isEmpty()){
                    ComandiGUI.ricercaAnnuncio(Acquisto.class, ComandiGUI.getUtenteCorrente());
                }else{
                    ComandiGUI.ricercaAnnuncio(this.chiaviField.getText(), Acquisto.class, ComandiGUI.getUtenteCorrente());
                }
            }else{
                JOptionPane.showMessageDialog(null, "Selezionare la tipologia di annuncio");
            }
        }else{
            if(vendita&&acquisto){
                if(chiaviField.getText().isEmpty()){
                    ComandiGUI.ricaricaBacheca();
                }else{
                    ComandiGUI.ricercaAnnuncio(this.chiaviField.getText());
                }
            }else if(vendita){
                if(chiaviField.getText().isEmpty()){
                    ComandiGUI.ricercaAnnuncio(Vendita.class);
                }else{
                    ComandiGUI.ricercaAnnuncio(this.chiaviField.getText(), Vendita.class);
                }
            }else if(acquisto){
                if(chiaviField.getText().isEmpty()){
                    ComandiGUI.ricercaAnnuncio(Acquisto.class);
                }else{
                    ComandiGUI.ricercaAnnuncio(this.chiaviField.getText(), Acquisto.class);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Selezionare la tipologia di annuncio");
            }
        }

    }

    /**
     * Metodo per la gestione della chiusura della finestra
     */
    private void chiusuraFinestra(WindowEvent e) {
        // Alla chiusura della finestra effettua reset della bacheca
        ComandiGUI.ricaricaBacheca();
    }

    /**
     * Inizializzazione componenti per il frame
     */
    private void initComponents() {
        chiaviLabel = new JLabel();
        chiaviField = new JTextField();
        tipoLabel = new JLabel();
        venditaCheck = new JCheckBox();
        acquistoCheck = new JCheckBox();
        ricercaButton = new JButton();
        mieiCheck = new JCheckBox();

        //======== this ========
        setTitle("Bacheca - Ricerca");
        setMinimumSize(new Dimension(400, 155));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                chiusuraFinestra(e);
            }
        });
        var contentPane = getContentPane();

        //---- chiaviLabel ----
        chiaviLabel.setText("Parole chiave:");

        //---- tipoLabel ----
        tipoLabel.setText("Tipologia annunci:");

        //---- venditaCheck ----
        venditaCheck.setText("Vendita");

        //---- acquistoCheck ----
        acquistoCheck.setText("Acquisto/Ricerca");

        //---- ricercaButton ----
        ricercaButton.setText("Ricerca");
        ricercaButton.addActionListener(e -> ricerca(e));

        //---- mieiCheck ----
        mieiCheck.setText("Miei");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(venditaCheck)
                        .addComponent(tipoLabel)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(acquistoCheck)
                                .addGap(40, 40, 40)
                                .addComponent(mieiCheck, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ricercaButton))
                            .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                .addComponent(chiaviLabel)
                                .addGap(18, 18, 18)
                                .addComponent(chiaviField, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(44, Short.MAX_VALUE))
        );
        contentPaneLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {acquistoCheck, venditaCheck});
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chiaviLabel)
                        .addComponent(chiaviField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(tipoLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(venditaCheck)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(acquistoCheck)
                        .addComponent(ricercaButton)
                        .addComponent(mieiCheck))
                    .addContainerGap(14, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JLabel chiaviLabel;
    private JTextField chiaviField;
    private JLabel tipoLabel;
    private JCheckBox venditaCheck;
    private JCheckBox acquistoCheck;
    private JButton ricercaButton;
    private JCheckBox mieiCheck;
}
