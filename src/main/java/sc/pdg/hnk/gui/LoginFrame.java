/*
 * Created by JFormDesigner on Wed Jan 15 15:58:21 CET 2025
 */

package sc.pdg.hnk.gui;

import sc.pdg.hnk.app.utente.PasswordException;
import sc.pdg.hnk.app.utente.UserListException;

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

    private void Login(ActionEvent e) {
        try{
            ComandiGUI.faiLogin(EmailField.getText(), new String(PasswordField.getPassword()));
            BachecaFrame bf = new BachecaFrame();
            bf.setVisible(true);
            this.dispose();
        }catch (UserListException | PasswordException x){
            JOptionPane.showMessageDialog(null, x.getMessage(),  "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Registrazione(ActionEvent e) {
        new RegistrazioneFrame().setVisible(true);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Cristian Capraro
        BenvenutoLabel = new JLabel();
        EmailField = new JTextField();
        PasswordField = new JPasswordField();
        EmailLabel = new JLabel();
        PasswordLabel = new JLabel();
        LoginButton = new JButton();
        RegistrazioneButton = new JButton();

        //======== this ========
        setTitle("Bacheca - Login");
        var contentPane = getContentPane();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        //---- BenvenutoLabel ----
        BenvenutoLabel.setText("Inserire le credenziali per accedere alla bacheca");
        BenvenutoLabel.setFont(BenvenutoLabel.getFont().deriveFont(Font.BOLD, BenvenutoLabel.getFont().getSize() + 2f));

        //---- EmailLabel ----
        EmailLabel.setText("Indirizzo Email");

        //---- PasswordLabel ----
        PasswordLabel.setText("Password");

        //---- LoginButton ----
        LoginButton.setText("Login");
        LoginButton.addActionListener(this::Login);

        //---- RegistrazioneButton ----
        RegistrazioneButton.setText("Registrati");
        RegistrazioneButton.addActionListener(this::Registrazione);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(BenvenutoLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(18, 18, 18))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(55, 55, 55)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(PasswordLabel)
                        .addComponent(EmailLabel)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(RegistrazioneButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LoginButton))
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(EmailField, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(PasswordField, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(BenvenutoLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                    .addGap(47, 47, 47)
                    .addComponent(EmailLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(EmailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(PasswordLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(PasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(LoginButton)
                        .addComponent(RegistrazioneButton))
                    .addGap(31, 31, 31))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Cristian Capraro
    private JLabel BenvenutoLabel;
    private JTextField EmailField;
    private JPasswordField PasswordField;
    private JLabel EmailLabel;
    private JLabel PasswordLabel;
    private JButton LoginButton;
    private JButton RegistrazioneButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
