/*
 * Created by JFormDesigner on Wed Jan 15 22:49:42 CET 2025
 */

package sc.pdg.hnk.gui;

import sc.pdg.hnk.app.bacheca.Bacheca;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Cristian
 */
public class BachecaFrame extends JFrame {
    private AggiuntaFrame aggiunta = new AggiuntaFrame();
    private RicercaFrame  ricerca = new RicercaFrame();

    public BachecaFrame() {
        initComponents();
    }

    private void aggiungiAnnuncio(ActionEvent e) {
        if(!aggiunta.isVisible()){
            aggiunta.setVisible(true);
        };
    }

    private void ricerca(ActionEvent e) {
        if(!this.ricerca.isVisible()){
            this.ricerca.setVisible(true);
        }
    }

    private void pulisci(ActionEvent e) {
        ComandiGUI.pulisciBacheca();
    }

    private void salva(ActionEvent e) {
        ComandiGUI.store();
        JOptionPane.showMessageDialog(null, "Bacheca salvata su file.");
    }

    private void logout(ActionEvent e) {
        this.dispose();
    }

    private void chiusuraFinestra(WindowEvent e) {
        // Salvataggio e logout
        ComandiGUI.faiLogout();
        new LoginFrame().setVisible(true);
    }

    private void modificaToggle(ActionEvent e) {
        if(rimuoviToggle.isSelected()){
            this.aggiungiButton.setEnabled(false);
            this.ricercaButton.setEnabled(false);
            this.pulisciButton.setEnabled(false);
            this.salvaButton.setEnabled(false);
            ComandiGUI.caricaAnnunciModificabili();
        }else{
            this.aggiungiButton.setEnabled(true);
            this.ricercaButton.setEnabled(true);
            this.pulisciButton.setEnabled(true);
            this.salvaButton.setEnabled(true);
            ComandiGUI.ricaricaBacheca();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Cristian Capraro
        toolbarBacheca = new JToolBar();
        aggiungiButton = new JButton();
        rimuoviToggle = new JToggleButton();
        ricercaButton = new JButton();
        pulisciButton = new JButton();
        salvaButton = new JButton();
        spaziatore = new JPanel(null);
        logoutButton = new JButton();
        scrollAnnunci = new JScrollPane();
        panelAnnunci = new JPanel();

        //======== this ========
        setTitle("Bacheca");
        setMinimumSize(new Dimension(640, 480));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                chiusuraFinestra(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(15, 8));

        //======== toolbarBacheca ========
        {
            toolbarBacheca.setFloatable(false);

            //---- aggiungiButton ----
            aggiungiButton.setText("Aggiungi");
            aggiungiButton.addActionListener(e -> aggiungiAnnuncio(e));
            toolbarBacheca.add(aggiungiButton);

            //---- rimuoviToggle ----
            rimuoviToggle.setText("Modifica");
            rimuoviToggle.addActionListener(e -> modificaToggle(e));
            toolbarBacheca.add(rimuoviToggle);

            //---- ricercaButton ----
            ricercaButton.setText("Ricerca");
            ricercaButton.addActionListener(e -> ricerca(e));
            toolbarBacheca.add(ricercaButton);

            //---- pulisciButton ----
            pulisciButton.setText("Pulisci");
            pulisciButton.addActionListener(e -> pulisci(e));
            toolbarBacheca.add(pulisciButton);

            //---- salvaButton ----
            salvaButton.setText("Salva");
            salvaButton.addActionListener(e -> salva(e));
            toolbarBacheca.add(salvaButton);
            toolbarBacheca.add(spaziatore);

            //---- logoutButton ----
            logoutButton.setText("Logout");
            logoutButton.addActionListener(e -> logout(e));
            toolbarBacheca.add(logoutButton);
        }
        contentPane.add(toolbarBacheca, BorderLayout.NORTH);

        //======== scrollAnnunci ========
        {

            //======== panelAnnunci ========
            {
                panelAnnunci.setLayout(new BoxLayout(panelAnnunci, BoxLayout.Y_AXIS));
            }
            scrollAnnunci.setViewportView(panelAnnunci);
        }
        contentPane.add(scrollAnnunci, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Cristian Capraro
    private JToolBar toolbarBacheca;
    private JButton aggiungiButton;
    private JToggleButton rimuoviToggle;
    private JButton ricercaButton;
    private JButton pulisciButton;
    private JButton salvaButton;
    private JPanel spaziatore;
    private JButton logoutButton;
    private JScrollPane scrollAnnunci;
    private JPanel panelAnnunci;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JPanel getPanelAnnunci() {
        return panelAnnunci;
    }
}
