/*
 * Created by JFormDesigner on Wed Jan 15 17:00:45 CET 2025
 */

package sc.pdg.hnk.gui;

import sc.pdg.hnk.app.annuncio.Annuncio;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Cristian
 */
public class BachecaFrame extends JFrame {
    public BachecaFrame() {
        initComponents();
        ComandiGUI.visualizzaTutti().forEach(this::add);
        pack();
        repaint();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Cristian Capraro
        ToolBar = new JToolBar();
        ScroolPane = new JScrollPane();

        //======== this ========
        setTitle("Bacheca");
        setMinimumSize(new Dimension(600, 480));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(ToolBar, BorderLayout.NORTH);
        contentPane.add(ScroolPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Cristian Capraro
    private JToolBar ToolBar;
    private JScrollPane ScroolPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
