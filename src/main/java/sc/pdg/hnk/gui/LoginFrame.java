/*
 * Created by JFormDesigner on Wed Jan 15 21:50:35 CET 2025
 */

package sc.pdg.hnk.gui;

import sc.pdg.hnk.app.utente.UserException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Cristian
 */
public class LoginFrame extends JFrame {
    public LoginFrame() {
        initComponents();
    }

    private void login(ActionEvent e) {
        // Effettua il login o gestisci eccezione utente
        try{
            ComandiGUI.faiLogin(this.emailField.getText(), new String(this.passwordField.getPassword()));
            ComandiGUI.lanciaBacheca();
            this.dispose();
        }catch (UserException ecc){
            JOptionPane.showMessageDialog(null, ecc.getMessage(), "Errore - Utente", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrazione(ActionEvent e) {
        // Apertura del Frame di registrazione
        new RegistrazioneFrame().setVisible(true);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Cristian Capraro
        benvenutoLabel = new JLabel();
        emailLabel = new JLabel();
        passwordLabel = new JLabel();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        registrazioneButton = new JButton();
        loginButton = new JButton();

        //======== this ========
        setTitle("Bacheca - Login");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();

        //---- benvenutoLabel ----
        benvenutoLabel.setText("Per poter accedere alla bacheca \u00e8 necessario effettuare l'accesso:");
        benvenutoLabel.setFont(benvenutoLabel.getFont().deriveFont(benvenutoLabel.getFont().getStyle() | Font.BOLD, benvenutoLabel.getFont().getSize() + 2f));

        //---- emailLabel ----
        emailLabel.setText("Email");

        //---- passwordLabel ----
        passwordLabel.setText("Password");

        //---- registrazioneButton ----
        registrazioneButton.setText("Registrazione");
        registrazioneButton.addActionListener(e -> registrazione(e));

        //---- loginButton ----
        loginButton.setText("Login");
        loginButton.addActionListener(e -> login(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(benvenutoLabel, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                            .addGap(18, 18, 18))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(loginButton)
                                    .addGap(18, 18, 18)
                                    .addComponent(registrazioneButton))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(emailLabel)
                                        .addComponent(passwordLabel))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(emailField, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                                        .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))))
                            .addGap(29, 29, 29))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(benvenutoLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(emailLabel)
                        .addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(11, 11, 11)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordLabel)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(15, 15, 15)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(registrazioneButton)
                        .addComponent(loginButton))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Cristian Capraro
    private JLabel benvenutoLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registrazioneButton;
    private JButton loginButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
