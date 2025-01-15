/*
 * Created by JFormDesigner on Wed Jan 15 17:13:52 CET 2025
 */

package sc.pdg.hnk.gui;

import sc.pdg.hnk.app.annuncio.Acquisto;
import sc.pdg.hnk.app.annuncio.Annuncio;
import sc.pdg.hnk.app.annuncio.Vendita;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.time.format.DateTimeFormatter;

/**
 * @author Cristian
 */
public class AnnuncioPanel extends JPanel {
    private Annuncio annuncio;
    
    public AnnuncioPanel(Annuncio annuncio) {
        initComponents();
        this.annuncio = annuncio;
        System.out.println("Arriva qui??");
        if(annuncio instanceof Vendita){
            // Annuncio di vendita
            this.TitoloLabel.setText(annuncio.getNome());
            this.TipoLabel.setText("Vendita");
            this.PrezzoLabel.setText(((Vendita) annuncio).getPrezzo() + " EUR");
            this.DescrizioneArea.setText(annuncio.getDescrizione());
            this.ScadenzaLabel.setText(((Vendita) annuncio).getScadenza().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            this.ProprietarioLabel.setText(annuncio.getProprietario().getNome());
            this.StatoLabel.setText(((Vendita) annuncio).getStato());
        }else if(annuncio instanceof Acquisto){
            // Annuncio di ricerca
            this.TitoloLabel.setText(annuncio.getNome());
            this.TipoLabel.setText("Ricerca");
            this.PrezzoLabel.setText(((Acquisto) annuncio).getMin() + "-" + ((Acquisto) annuncio).getMax());
            this.DescrizioneArea.setText(annuncio.getDescrizione());
            this.ScadenzaLabel.setVisible(false);
            this.ProprietarioLabel.setText(annuncio.getProprietario().getNome());
            this.StatoLabel.setVisible(false);
        }
        System.out.println("Arriva qui?? v2");
        this.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Cristian Capraro
        TitoloLabel = new JLabel();
        scrollPane1 = new JScrollPane();
        DescrizioneArea = new JTextArea();
        PrezzoLabel = new JLabel();
        StatoLabel = new JLabel();
        TipoLabel = new JLabel();
        ProprietarioLabel = new JLabel();
        ScadenzaLabel = new JLabel();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
        . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder
        . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .
        awt .Font .BOLD ,12 ), java. awt. Color. red) , getBorder( )) )
        ;  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
        ;

        //---- TitoloLabel ----
        TitoloLabel.setText("Titolo annuncio");

        //======== scrollPane1 ========
        {

            //---- DescrizioneArea ----
            DescrizioneArea.setText("Descrizione annuncio");
            scrollPane1.setViewportView(DescrizioneArea);
        }

        //---- PrezzoLabel ----
        PrezzoLabel.setText("Prezzo");

        //---- StatoLabel ----
        StatoLabel.setText("Stato");

        //---- TipoLabel ----
        TipoLabel.setText("Tipo annuncio");

        //---- ProprietarioLabel ----
        ProprietarioLabel.setText("Proprietario");

        //---- ScadenzaLabel ----
        ScadenzaLabel.setText("Scadenza");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(TitoloLabel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TipoLabel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 530, GroupLayout.PREFERRED_SIZE)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(StatoLabel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(ProprietarioLabel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(ScadenzaLabel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PrezzoLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(TitoloLabel)
                        .addComponent(TipoLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(PrezzoLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(StatoLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                        .addComponent(ProprietarioLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                        .addComponent(ScadenzaLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Cristian Capraro
    private JLabel TitoloLabel;
    private JScrollPane scrollPane1;
    private JTextArea DescrizioneArea;
    private JLabel PrezzoLabel;
    private JLabel StatoLabel;
    private JLabel TipoLabel;
    private JLabel ProprietarioLabel;
    private JLabel ScadenzaLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
