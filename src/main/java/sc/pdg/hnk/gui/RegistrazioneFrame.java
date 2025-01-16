/*
 * Created by JFormDesigner on Wed Jan 15 22:04:40 CET 2025
 */

package sc.pdg.hnk.gui;

import sc.pdg.hnk.app.utente.UserException;
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

    private void indietro(ActionEvent e) {
        // Riapri la sessione login
        new LoginFrame().setVisible(true);
        this.dispose();
    }

    private void registrazione(ActionEvent e) {
        // Continua con il login utente e accesso alla bacheca
        try{
            ComandiGUI.faiRegistrazione(this.emailField.getText(), this.passwordField.getText(), this.usernameField.getText());
            JOptionPane.showMessageDialog(null, "Registrazione utente effettuata con successo.", "Account aggiunto", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }catch (UserListException ecc){
            JOptionPane.showMessageDialog(null, ecc.getMessage(), "Errore - Registrazione", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void chiusuraFinestra(WindowEvent e) {
        new LoginFrame().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Cristian Capraro
        benvenutoLabel = new JLabel();
        usernameField = new JTextField();
        emailField = new JTextField();
        usernameLabel = new JLabel();
        emailLabel = new JLabel();
        passwordField = new JTextField();
        passwordLabel = new JLabel();
        registrazioneButton = new JButton();
        indietroButton = new JButton();

        //======== this ========
        setTitle("Bacheca - Registrazione nuovo utente");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                chiusuraFinestra(e);
            }
        });
        var contentPane = getContentPane();

        //---- benvenutoLabel ----
        benvenutoLabel.setText("Crezione di un nuovo account utente");
        benvenutoLabel.setFont(benvenutoLabel.getFont().deriveFont(benvenutoLabel.getFont().getStyle() | Font.BOLD, benvenutoLabel.getFont().getSize() + 2f));

        //---- usernameLabel ----
        usernameLabel.setText("Nome Utente");

        //---- emailLabel ----
        emailLabel.setText("Email");

        //---- passwordLabel ----
        passwordLabel.setText("Password");

        //---- registrazioneButton ----
        registrazioneButton.setText("Registrati");
        registrazioneButton.addActionListener(e -> registrazione(e));

        //---- indietroButton ----
        indietroButton.setText("Torna indietro");
        indietroButton.addActionListener(e -> indietro(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(benvenutoLabel, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addComponent(emailField, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailLabel)
                            .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordLabel)
                            .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameLabel)
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(registrazioneButton)
                                .addGap(18, 18, 18)
                                .addComponent(indietroButton))))
                    .addContainerGap(21, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(benvenutoLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(usernameLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(emailLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(passwordLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(registrazioneButton)
                        .addComponent(indietroButton))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Cristian Capraro
    private JLabel benvenutoLabel;
    private JTextField usernameField;
    private JTextField emailField;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JTextField passwordField;
    private JLabel passwordLabel;
    private JButton registrazioneButton;
    private JButton indietroButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
