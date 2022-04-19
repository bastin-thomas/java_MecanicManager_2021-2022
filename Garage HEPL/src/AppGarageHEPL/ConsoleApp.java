/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppGarageHEPL;
import people.*;

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
        PersonnelGarage c1 = new PersonnelGarage();
        System.out.print(c1 + "\n");
        
        System.out.print("Constructeur par Init:\n");
        PersonnelGarage c2 = new PersonnelGarage("MAT0001","Bastin","Thomas","95 Rue Basse Mehagne","+32 470783771");
        System.out.print(c2 + "\n");
    }
    
}
