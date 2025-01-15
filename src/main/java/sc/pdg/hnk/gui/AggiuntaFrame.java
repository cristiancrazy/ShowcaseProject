/*
 * Created by JFormDesigner on Wed Jan 15 23:23:09 CET 2025
 */

package sc.pdg.hnk.gui;

import java.awt.event.*;

import sc.pdg.hnk.app.annuncio.Acquisto;
import sc.pdg.hnk.app.annuncio.Annuncio;
import sc.pdg.hnk.app.annuncio.Vendita;
import sc.pdg.hnk.app.bacheca.Bacheca;
import sc.pdg.hnk.app.utente.Utente;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.event.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Cristian
 */
public class AggiuntaFrame extends JFrame {
    @SuppressWarnings("unchecked")
    public AggiuntaFrame() {
        initComponents();
        for (Vendita.Condizioni value : Vendita.Condizioni.values()) {
            this.statoComboBox.addItem(value.name());
        }
    }

    private void venditaButton(ActionEvent e) {
        this.prezzoField.setEnabled(true);
        this.statoComboBox.setEnabled(true);
        this.maxField.setEnabled(false);
        this.minField.setEnabled(false);    }

        private void acquistoButton(ActionEvent e) {
            this.prezzoField.setEnabled(false);
            this.statoComboBox.setEnabled(false);
            this.maxField.setEnabled(true);
            this.minField.setEnabled(true);
    }

    private void venditaStateChanged(ChangeEvent e) {
        // TODO add your code here
    }

    private void acquistoStateChanged(ChangeEvent e) {
        // TODO add your code here
    }

    private void inserisci(ActionEvent e) {
        if(this.venditaButton.isSelected()){
            String nome, descrizione, stato, prezzo, chiavi;
            double prezzoFinale;
            Utente corrente = ComandiGUI.getUtenteCorrente();
            nome = this.nomeField.getText();
            descrizione = this.descrizioneField.getText();
            chiavi = this.chiaviField.getText();
            stato = Objects.requireNonNull(this.statoComboBox.getSelectedItem()).toString();
            prezzo = prezzoField.getText();
            try {
                prezzoFinale = Double.parseDouble(prezzo);
            }catch (Exception ecc){
                JOptionPane.showMessageDialog(null, "Prezzo non valido.", "Effettuare operazione", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ComandiGUI.aggiungiAnnuncio(new Vendita(nome, descrizione, corrente, chiavi, prezzoFinale, Vendita.Condizioni.valueOf(stato)));

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
            ComandiGUI.aggiungiAnnuncio(new Acquisto(nome, descrizione, corrente, chiavi, minFinale, maxFinale));
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

        //======== this ========
        setTitle("Bacheca - Inserimento annuncio");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();

        //---- tipologiaLabel ----
        tipologiaLabel.setText("Tipologia");

        //---- venditaButton ----
        venditaButton.setText("Vendita");
        venditaButton.addChangeListener(e -> venditaStateChanged(e));
        venditaButton.addActionListener(e -> venditaButton(e));

        //---- acquistoButton ----
        acquistoButton.setText("Ricerca");
        acquistoButton.addChangeListener(e -> acquistoStateChanged(e));
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

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
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
                                .addComponent(descrizioneScroll, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                                .addComponent(nomeField, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)))
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
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(chiaviField, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                .addComponent(maxLabel)
                                .addComponent(maxField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))))
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
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(descrizioneScroll)
                        .addComponent(descrizioneLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addComponent(maxLabel))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(maxField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(minField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chiaviField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(chiaviLabel))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
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
    private JComboBox statoComboBox;
    private JLabel statoLabel;
    private JButton inserisciButton;
    private JTextField chiaviField;
    private JLabel chiaviLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
