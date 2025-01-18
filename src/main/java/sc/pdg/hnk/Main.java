package sc.pdg.hnk;
import sc.pdg.hnk.cli.ComandiCLI;
import sc.pdg.hnk.gui.ComandiGUI;

import java.nio.file.Path;
import java.util.Arrays;

/**
 * Entry-point dell'applicazione bacheca
 */
public class Main {
    public static void main(String[] args) {
        Path fileBacheca = null;
        boolean fileFlag = false;
        boolean cliFlag = false;

        // Parsing argomenti linea di comando
        for(String arg : args){
            switch (arg.split(":")[0]){
                case "-file" -> {
                    String filepath = arg.split(":")[1].replace("\"", "");
                    if(Path.of(filepath).toFile().exists()){
                        fileBacheca = Path.of(filepath);
                        fileFlag = true;
                    }else{
                        System.out.println("File bacheca non valido!");
                        return;
                    }
                }
                case "-cli" -> {
                    cliFlag = true;
                }
            }
        }

        if(fileFlag){
            if(cliFlag){
                new ComandiCLI(fileBacheca);
            }else{
                ComandiGUI.lanciaGUI(fileBacheca);
            }
        }else{
            if(cliFlag){
                new ComandiCLI();
            }else{
                ComandiGUI.lanciaGUI();
            }
        }
    }
}