package sc.pdg.hnk;
import sc.pdg.hnk.cli.ComandiCLI;
import sc.pdg.hnk.gui.ComandiGUI;

/**
 * Entry-point dell'applicazione bacheca
 */
public class Main {
    public static void main(String[] args) {
        if(args.length > 1){
            if(args[1].equals("--cli")){
                new ComandiCLI();
            }else if(args[1].equals("--gui")){
                ComandiGUI.lanciaGUI();
            }else{
                System.out.println("parametro: " + args[1] + " non valido");
                System.out.println("--gui\tper lanciare l'interfaccia grafica");
                System.out.println("--cli\tper lanciare l'interfaccia testuale");
            }
        }
        // Default
        ComandiGUI.lanciaGUI();
    }
}