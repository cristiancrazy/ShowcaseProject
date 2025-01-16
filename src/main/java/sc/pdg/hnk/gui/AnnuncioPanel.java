/*
 * Created by JFormDesigner on Wed Jan 15 22:32:36 CET 2025
 */

package sc.pdg.hnk.gui;

import sc.pdg.hnk.app.annuncio.Acquisto;
import sc.pdg.hnk.app.annuncio.Annuncio;
import sc.pdg.hnk.app.annuncio.Vendita;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;

/**
 * @author Cristian
 */
public class AnnuncioPanel extends JPanel {
    public AnnuncioPanel(Annuncio annuncio) {
        initComponents();
        // Parsing dell'annuncio
        this.setBorder(new LineBorder(Color.BLACK));
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Cristian Capraro
        nomeLabel = new JLabel();
        wrapperDescrizione = new JScrollPane();
        descrizioneField = new JTextPane();
        proprietarioLabel = new JLabel();
        statoLabel = new JLabel();
        scadenzaLabel = new JLabel();
        prezzoLabel = new JLabel();

        //======== this ========

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
                            .addGap(0, 362, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(proprietarioLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(statoLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                            .addComponent(scadenzaLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(0, 268, Short.MAX_VALUE)
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
                    .addComponent(prezzoLabel, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Cristian Capraro
    private JLabel nomeLabel;
    private JScrollPane wrapperDescrizione;
    private JTextPane descrizioneField;
    private JLabel proprietarioLabel;
    private JLabel statoLabel;
    private JLabel scadenzaLabel;
    private JLabel prezzoLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
