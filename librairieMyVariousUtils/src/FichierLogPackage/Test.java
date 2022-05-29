package FichierLogPackage;


import FichierLogPackage.FichierLog;
import java.io.IOException;
/**
 *
 * @author student
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        FichierLog test = new FichierLog();
        System.out.println(test.Length());
        
        test.PrintLN("Test","Fichier Enregistré");
        test.PrintLN("Test","Ligne 2");
        test.PrintLN("Test","Ligne 3");
        test.PrintLN("Test","Ligne 4");
        test.PrintLN("Test","Ligne 5");
        test.PrintLN("Test","Ligne 6");
        test.PrintLN("Test","Fichier Fin");
        
        System.out.println(test.Length());
    }
}
