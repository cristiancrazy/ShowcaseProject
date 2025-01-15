/*
 * Created by JFormDesigner on Wed Jan 15 16:37:36 CET 2025
 */

package sc.pdg.hnk.gui;

import sc.pdg.hnk.app.utente.UserListException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Cristian
 */
public class RegistrazioneFrame extends JFrame {
    public RegistrazioneFrame() {
        initComponents();
    }

    private void TornaIndietro(ActionEvent e) {
        new LoginFrame().setVisible(true);
        this.dispose();
    }

    private void Registrazione(ActionEvent e) {
        try{
            ComandiGUI.faiRegistrazione(this.EmailField.getText(), this.PasswordField.getText(), this.NomeField.getText());
        }catch (UserListException x){
            JOptionPane.showMessageDialog(null, x.getMessage(),  "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Cristian Capraro
        CreazioneLabel = new JLabel();
        EmailField = new JTextField();
        NomeField = new JTextField();
        PasswordField = new JTextField();
        TornaButton = new JButton();
        RegistrazioneButton = new JButton();
        EmailLabel = new JLabel();
        UtenteLabel = new JLabel();
        PasswordLabel = new JLabel();

        //======== this ========
        setTitle("Bacheca - Registrazione Utente");
        var contentPane = getContentPane();
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //---- CreazioneLabel ----
        CreazioneLabel.setText("Registrazione - Nuovo utente");
        CreazioneLabel.setFont(CreazioneLabel.getFont().deriveFont(Font.BOLD, CreazioneLabel.getFont().getSize() + 2f));

        //---- TornaButton ----
        TornaButton.setText("Torna indietro");
        TornaButton.addActionListener(this::TornaIndietro);

        //---- RegistrazioneButton ----
        RegistrazioneButton.setText("Effettua registrazione");
        RegistrazioneButton.addActionListener(this::Registrazione);

        //---- EmailLabel ----
        EmailLabel.setText("Email");

        //---- UtenteLabel ----
        UtenteLabel.setText("Nome utente");

        //---- PasswordLabel ----
        PasswordLabel.setText("Nuova password");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(PasswordLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addComponent(UtenteLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addComponent(CreazioneLabel)
                        .addComponent(PasswordField, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
                        .addComponent(NomeField, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
                        .addComponent(EmailField, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(RegistrazioneButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TornaButton))
                        .addComponent(EmailLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(58, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(CreazioneLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                    .addGap(24, 24, 24)
                    .addComponent(EmailLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(EmailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(UtenteLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(NomeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(3, 3, 3)
                    .addComponent(PasswordLabel)
                    .addGap(2, 2, 2)
                    .addComponent(PasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(RegistrazioneButton)
                        .addComponent(TornaButton))
                    .addGap(40, 40, 40))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Cristian Capraro
    private JLabel CreazioneLabel;
    private JTextField EmailField;
    private JTextField NomeField;
    private JTextField PasswordField;
    private JButton TornaButton;
    private JButton RegistrazioneButton;
    private JLabel EmailLabel;
    private JLabel UtenteLabel;
    private JLabel PasswordLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
