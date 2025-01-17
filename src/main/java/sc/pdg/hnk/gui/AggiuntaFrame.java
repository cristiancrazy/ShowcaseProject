/*
 * Created by JFormDesigner on Wed Jan 15 23:23:09 CET 2025
 */

package sc.pdg.hnk.gui;

import java.awt.*;
import java.awt.event.*;

import sc.pdg.hnk.app.annuncio.Acquisto;
import sc.pdg.hnk.app.annuncio.Annuncio;
import sc.pdg.hnk.app.annuncio.AnnuncioException;
import sc.pdg.hnk.app.annuncio.Vendita;
import sc.pdg.hnk.app.bacheca.Bacheca;
import sc.pdg.hnk.app.utente.Utente;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Cristian
 */
public class AggiuntaFrame extends JFrame {

    public AggiuntaFrame() {
        initComponents();
        for (Vendita.Condizioni value : Vendita.Condizioni.values()) {
            this.statoComboBox.addItem(value.name());
        }
    }

    private void venditaButton(ActionEvent e) {
        this.scadenzaField.setEnabled(true);
        this.prezzoField.setEnabled(true);
        this.statoComboBox.setEnabled(true);
        this.maxField.setEnabled(false);
        this.minField.setEnabled(false);
    }

        private void acquistoButton(ActionEvent e) {
            this.scadenzaField.setEnabled(false);
            this.prezzoField.setEnabled(false);
            this.statoComboBox.setEnabled(false);
            this.maxField.setEnabled(true);
            this.minField.setEnabled(true);
    }

    private void inserisci(ActionEvent e) {
        if(this.venditaButton.isSelected()){
            String nome, descrizione, stato, prezzo, chiavi;
            String scadenza;
            double prezzoFinale;
            Utente corrente = ComandiGUI.getUtenteCorrente();
            nome = this.nomeField.getText();
            descrizione = this.descrizioneField.getText();
            chiavi = this.chiaviField.getText();
            stato = Objects.requireNonNull(this.statoComboBox.getSelectedItem()).toString();
            prezzo = prezzoField.getText();
            scadenza = scadenzaField.getText();
            try {
                prezzoFinale = Double.parseDouble(prezzo);
            }catch (Exception ecc){
                JOptionPane.showMessageDialog(null, "Prezzo non valido.", "Effettuare operazione", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if(!scadenza.isEmpty()){
                try {
                    LocalDate ld = LocalDate.parse(scadenza, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    ComandiGUI.aggiungiAnnuncio(new Vendita(nome, descrizione, corrente, chiavi, prezzoFinale, ld, Vendita.Condizioni.valueOf(stato)));
                }catch (DateTimeParseException ecc){
                    JOptionPane.showMessageDialog(null, "Data scadenza non valida.", "Effettuare operazione", JOptionPane.WARNING_MESSAGE);
                }catch (AnnuncioException ecc){
                    JOptionPane.showMessageDialog(null, ecc.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
                return;
            }

            try{
                ComandiGUI.aggiungiAnnuncio(new Vendita(nome, descrizione, corrente, chiavi, prezzoFinale, Vendita.Condizioni.valueOf(stato)));
            }catch (AnnuncioException ecc){
                JOptionPane.showMessageDialog(null, ecc.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }


        }else if(this.acquistoButton.isSelected()){
            String nome, descrizione, chiavi, max, min;
            double minFinale, maxFinale;
            Utente corrente = ComandiGUI.getUtenteCorrente();
            nome = this.nomeField.getText();
            descrizione = this.descrizioneField.getText();
            chiavi = this.chiaviField.getText();
            min = this.minField.getText();
            max = this.maxField.getText();
            try {
                maxFinale = Double.parseDouble(max);
                minFinale = Double.parseDouble(min);
            }catch (Exception ecc){
                JOptionPane.showMessageDialog(null, "Budget range non valido.", "Effettuare operazione", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try{
                ComandiGUI.aggiungiAnnuncio(new Acquisto(nome, descrizione, corrente, chiavi, minFinale, maxFinale));
            }catch (AnnuncioException ecc){
                JOptionPane.showMessageDialog(null, ecc.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Si prega di selezionare la tipologia di annuncio.", "Effettuare operazione", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Cristian Capraro
        tipologiaLabel = new JLabel();
        venditaButton = new JRadioButton();
        acquistoButton = new JRadioButton();
        nomeLabel = new JLabel();
        nomeField = new JTextField();
        descrizioneScroll = new JScrollPane();
        descrizioneField = new JTextArea();
        descrizioneLabel = new JLabel();
        prezzoField = new JTextField();
        prezzoLabel = new JLabel();
        maxField = new JTextField();
        minField = new JTextField();
        minLabel = new JLabel();
        maxLabel = new JLabel();
        statoComboBox = new JComboBox();
        statoLabel = new JLabel();
        inserisciButton = new JButton();
        chiaviField = new JTextField();
        chiaviLabel = new JLabel();
        scadenzaField = new JTextField();
        scadenzaLabel = new JLabel();

        //======== this ========
        setTitle("Bacheca - Inserimento annuncio");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();

        //---- tipologiaLabel ----
        tipologiaLabel.setText("Tipologia");

        //---- venditaButton ----
        venditaButton.setText("Vendita");
        venditaButton.addActionListener(e -> venditaButton(e));

        //---- acquistoButton ----
        acquistoButton.setText("Ricerca");
        acquistoButton.addActionListener(e -> acquistoButton(e));

        //---- nomeLabel ----
        nomeLabel.setText("Nome");

        //======== descrizioneScroll ========
        {
            descrizioneScroll.setViewportView(descrizioneField);
        }

        //---- descrizioneLabel ----
        descrizioneLabel.setText("Desc");

        //---- prezzoField ----
        prezzoField.setEnabled(false);

        //---- prezzoLabel ----
        prezzoLabel.setText("Prezzo");

        //---- maxField ----
        maxField.setEnabled(false);

        //---- minField ----
        minField.setText("0.0");
        minField.setEnabled(false);

        //---- minLabel ----
        minLabel.setText("Budget minimo");

        //---- maxLabel ----
        maxLabel.setText("Budget massimo");

        //---- statoComboBox ----
        statoComboBox.setEnabled(false);

        //---- statoLabel ----
        statoLabel.setText("Stato");

        //---- inserisciButton ----
        inserisciButton.setText("Inserisci annuncio");
        inserisciButton.addActionListener(e -> inserisci(e));

        //---- chiaviLabel ----
        chiaviLabel.setText("Chiavi");

        //---- scadenzaField ----
        scadenzaField.setEnabled(false);

        //---- scadenzaLabel ----
        scadenzaLabel.setText("Data scadenza: (dd-mm-yyyy)");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(inserisciButton)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(tipologiaLabel)
                            .addGap(18, 18, 18)
                            .addComponent(venditaButton)
                            .addGap(18, 18, 18)
                            .addComponent(acquistoButton))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(statoLabel)
                            .addGap(18, 18, 18)
                            .addComponent(statoComboBox, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(nomeLabel)
                                .addComponent(descrizioneLabel)
                                .addComponent(prezzoLabel))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(prezzoField, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                                .addComponent(nomeField, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                                .addComponent(descrizioneScroll, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(minField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(minLabel))
                                    .addGap(18, 18, 18))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(chiaviLabel)
                                    .addGap(9, 9, 9)))
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(maxField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(maxLabel))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(scadenzaLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(scadenzaField)))
                                .addComponent(chiaviField))))
                    .addContainerGap(21, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(tipologiaLabel)
                        .addComponent(venditaButton)
                        .addComponent(acquistoButton))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nomeLabel)
                        .addComponent(nomeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(descrizioneLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                        .addComponent(descrizioneScroll, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(prezzoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(prezzoLabel))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(statoLabel)
                        .addComponent(statoComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(minLabel)
                        .addComponent(maxLabel)
                        .addComponent(scadenzaLabel))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(maxField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(minField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(scadenzaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chiaviLabel)
                        .addComponent(chiaviField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(9, 9, 9)
                    .addComponent(inserisciButton)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());

        //---- tipologiaAnnuncioGroup ----
        var tipologiaAnnuncioGroup = new ButtonGroup();
        tipologiaAnnuncioGroup.add(venditaButton);
        tipologiaAnnuncioGroup.add(acquistoButton);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Cristian Capraro
    private JLabel tipologiaLabel;
    private JRadioButton venditaButton;
    private JRadioButton acquistoButton;
    private JLabel nomeLabel;
    private JTextField nomeField;
    private JScrollPane descrizioneScroll;
    private JTextArea descrizioneField;
    private JLabel descrizioneLabel;
    private JTextField prezzoField;
    private JLabel prezzoLabel;
    private JTextField maxField;
    private JTextField minField;
    private JLabel minLabel;
    private JLabel maxLabel;
    private JComboBox<String> statoComboBox;
    private JLabel statoLabel;
    private JButton inserisciButton;
    private JTextField chiaviField;
    private JLabel chiaviLabel;
    private JTextField scadenzaField;
    private JLabel scadenzaLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
