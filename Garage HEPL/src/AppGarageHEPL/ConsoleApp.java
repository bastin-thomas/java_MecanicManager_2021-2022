/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppGarageHEPL;
import people.*;
import vehicules.MissingTradeMarkException;
import vehicules.TypeVoiture;
import vehicules.Voiture;

/**
 *
 * @author student
 */
public class ConsoleApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.print("\n\nTest Class Client:\n");
        System.out.print("Constructeur par Défault:\n");
        Mecanicien c1 = new Mecanicien();
        System.out.print(c1 + "\n");
        
        System.out.print("Constructeur par Init:\n");
        Mecanicien c2 = new Mecanicien("MAT0001","Bastin","Thomas","95 Rue Basse Mehagne","+32 470783771");
        System.out.print(c2 + "\n");
        
        
        try
        {
            Voiture v1 = new Voiture(new Client("Coucou","Test"),new TypeVoiture(null,"test",10));
        }
        catch(MissingTradeMarkException e){
            System.out.print("Exception Lancée" + "\n");
        }

        
    
    }
    
}
