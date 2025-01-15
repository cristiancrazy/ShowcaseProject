package sc.pdg.hnk;
import sc.pdg.hnk.cli.ComandiCLI;
import sc.pdg.hnk.gui.ComandiGUI;
import sc.pdg.hnk.gui.LoginFrame;

/**
 * Entry-point dell'applicazione bacheca
 */
public class Main {
    public static void main(String[] args) {
        ComandiGUI.lanciaGUI();
        //new ComandiCLI();
    }
}