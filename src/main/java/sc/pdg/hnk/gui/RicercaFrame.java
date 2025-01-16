/*
 * Created by JFormDesigner on Thu Jan 16 10:10:31 CET 2025
 */

package sc.pdg.hnk.gui;

import sc.pdg.hnk.app.annuncio.Acquisto;
import sc.pdg.hnk.app.annuncio.Vendita;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Cristian
 */
public class RicercaFrame extends JFrame {
    public RicercaFrame() {
        initComponents();
    }

    private void ricerca(ActionEvent e) {
        boolean vendita = this.venditaCheck.isSelected();
        boolean acquisto = this.acquistoCheck.isSelected();

        if(chiaviField.getText().isEmpty()&&!this.mieiCheck.isSelected()){
            JOptionPane.showMessageDialog(null, "Inserire almeno una chiave");
            return;
        }

        if(mieiCheck.isSelected()){
            if(vendita&&acquisto){
                if(chiaviField.getText().isEmpty()){
                    ComandiGUI.ricercaAnnunciUtente(null, ComandiGUI.getUtenteCorrente());
                }else{
                    ComandiGUI.ricercaAnnunciUtente(chiaviField.getText(), null, ComandiGUI.getUtenteCorrente());
                }
            }else if(vendita){
                if(chiaviField.getText().isEmpty()){
                    ComandiGUI.ricercaAnnunciUtente(Vendita.class, ComandiGUI.getUtenteCorrente());
                }else{
                    ComandiGUI.ricercaAnnunciUtente(this.chiaviField.getText(), Vendita.class, ComandiGUI.getUtenteCorrente());
                }
            }else if(acquisto){
                if(chiaviField.getText().isEmpty()){
                    ComandiGUI.ricercaAnnunciUtente(Acquisto.class, ComandiGUI.getUtenteCorrente());
                }else{
                    ComandiGUI.ricercaAnnunciUtente(this.chiaviField.getText(), Acquisto.class, ComandiGUI.getUtenteCorrente());
                }
            }else{
                JOptionPane.showMessageDialog(null, "Selezionare la tipologia di annuncio");
            }
        }else{
            if(vendita&&acquisto){
                ComandiGUI.ricercaAnnuncio(this.chiaviField.getText(), null);
            }else if(vendita){
                ComandiGUI.ricercaAnnuncio(this.chiaviField.getText(), Vendita.class);
            }else if(acquisto){
                ComandiGUI.ricercaAnnuncio(this.chiaviField.getText(), Acquisto.class);
            }else{
                JOptionPane.showMessageDialog(null, "Selezionare la tipologia di annuncio");
            }
        }

    }

    private void chiusuraFinestra(WindowEvent e) {
        // Alla chiusura della finestra effettua reset della bacheca
        ComandiGUI.ricaricaBacheca();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Cristian Capraro
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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Cristian Capraro
    private JLabel chiaviLabel;
    private JTextField chiaviField;
    private JLabel tipoLabel;
    private JCheckBox venditaCheck;
    private JCheckBox acquistoCheck;
    private JButton ricercaButton;
    private JCheckBox mieiCheck;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
