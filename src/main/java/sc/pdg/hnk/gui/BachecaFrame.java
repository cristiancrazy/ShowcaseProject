package sc.pdg.hnk.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BachecaFrame extends JFrame {
    private final AggiuntaFrame aggiunta = new AggiuntaFrame();
    private final RicercaFrame  ricerca = new RicercaFrame();

    /**
     * Richiama initComponent
     */
    public BachecaFrame() {
        initComponents();
    }

    /**
     * rende visibile il form per aggiungere un annuncio
     */
    private void aggiungiAnnuncio(ActionEvent e) {
        if(!aggiunta.isVisible()){
            aggiunta.setVisible(true);
        }
    }

    /**
     *Rende visibile il form per la ricerca dell0'annuncio
     */
    private void ricerca(ActionEvent e) {
        if(!this.ricerca.isVisible()){
            this.ricerca.setVisible(true);
        }
    }

    /**
     * Pulisce la bacheca
     */
    private void pulisci(ActionEvent e) {
        ComandiGUI.pulisciBacheca();
    }

    /**
     * Salva la bacheca Su file
     */
    private void salva(ActionEvent e) {
        ComandiGUI.store();
        JOptionPane.showMessageDialog(null, "Bacheca salvata su file.");
    }

    /**
     *Riavvia un nuova sessione
     */
    private void logout(ActionEvent e) {
        this.dispose();
    }

    /**
     *Chiude la bacheca e predispone un nuovo login
     */
    private void chiusuraFinestra(WindowEvent e) {
        // Salvataggio e logout
        ComandiGUI.faiLogout();
        new LoginFrame().setVisible(true);
    }

    /**
     *Blocca le funzioni della bacheca quando è in corso la modifica e le ripristina quando finisce
     */
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

    /**
     * Inizializza i componenti della bacheca
     */
    private void initComponents() {
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
            aggiungiButton.addActionListener(this::aggiungiAnnuncio);
            toolbarBacheca.add(aggiungiButton);

            //---- rimuoviToggle ----
            rimuoviToggle.setText("Modifica");
            rimuoviToggle.addActionListener(this::modificaToggle);
            toolbarBacheca.add(rimuoviToggle);

            //---- ricercaButton ----
            ricercaButton.setText("Ricerca");
            ricercaButton.addActionListener(this::ricerca);
            toolbarBacheca.add(ricercaButton);

            //---- pulisciButton ----
            pulisciButton.setText("Pulisci");
            pulisciButton.addActionListener(this::pulisci);
            toolbarBacheca.add(pulisciButton);

            //---- salvaButton ----
            salvaButton.setText("Salva");
            salvaButton.addActionListener(this::salva);
            toolbarBacheca.add(salvaButton);
            toolbarBacheca.add(spaziatore);

            //---- logoutButton ----
            logoutButton.setText("Logout");
            logoutButton.addActionListener(this::logout);
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
    }

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

    /**
     * Getter del pannello
     * @return Pannello annunci
     */
    public JPanel getPanelAnnunci() {
        return panelAnnunci;
    }
}
