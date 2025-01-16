/*
 * Created by JFormDesigner on Wed Jan 15 22:49:42 CET 2025
 */

package sc.pdg.hnk.gui;

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

    private void rimuovi(ActionEvent e) {
        // TODO add your code here
    }

    private void filtra(ActionEvent e) {
        // TODO add your code here
    }

    private void ricerca(ActionEvent e) {
        if(!this.ricerca.isVisible()){
            this.ricerca.setVisible(true);
        }
    }

    private void pulisci(ActionEvent e) {
        // TODO add your code here
    }

    private void salva(ActionEvent e) {
        ComandiGUI.store();
        JOptionPane.showMessageDialog(null, "Bacheca salvata su file.");
    }

    private void logout(ActionEvent e) {
        this.dispose();
        ComandiGUI.faiLogout();
        new LoginFrame().setVisible(true);
    }

    private void chiusuraFinestra(WindowEvent e) {
        // Salvataggio e logout
        ComandiGUI.faiLogout();
        new LoginFrame().setVisible(true);
    }

    private void aggiungi(ActionEvent e) {
        // TODO add your code here
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Cristian Capraro
        toolbarBacheca = new JToolBar();
        aggiungiButton = new JButton();
        rimuoviButton = new JButton();
        filtraButton = new JButton();
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
            aggiungiButton.addActionListener(e -> {
			aggiungi(e);
			aggiungiAnnuncio(e);
		});
            toolbarBacheca.add(aggiungiButton);

            //---- rimuoviButton ----
            rimuoviButton.setText("Rimuovi");
            rimuoviButton.addActionListener(e -> {
			aggiungi(e);
			rimuovi(e);
		});
            toolbarBacheca.add(rimuoviButton);

            //---- filtraButton ----
            filtraButton.setText("Filtra");
            filtraButton.addActionListener(e -> {
			aggiungi(e);
			filtra(e);
		});
            toolbarBacheca.add(filtraButton);

            //---- ricercaButton ----
            ricercaButton.setText("Ricerca");
            ricercaButton.addActionListener(e -> {
			aggiungi(e);
			ricerca(e);
			ricerca(e);
		});
            toolbarBacheca.add(ricercaButton);

            //---- pulisciButton ----
            pulisciButton.setText("Pulisci");
            pulisciButton.addActionListener(e -> {
			aggiungi(e);
			pulisci(e);
		});
            toolbarBacheca.add(pulisciButton);

            //---- salvaButton ----
            salvaButton.setText("Salva");
            salvaButton.addActionListener(e -> {
			aggiungi(e);
			salva(e);
		});
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
    private JButton rimuoviButton;
    private JButton filtraButton;
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
