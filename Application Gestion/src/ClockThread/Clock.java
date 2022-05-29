/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClockThread;

import GUI.Atelier;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class Clock extends Thread {
    
    private Atelier Parent;
    
    public Clock(Atelier parent) {
        //Recupération ref Atelier et jlabel set 0
        Parent = parent;
        Parent.getjLabel_HoroDateHeure().setText("");
    }
    
    @Override
    public void run(){
        
        //Boucle Infinie pour refresh
        while(true){
            
            //Récupération Format dans le container
            SimpleDateFormat Format = Parent.getContainer().getDateformat();
            
            //Affichage de la date
            Parent.getjLabel_HoroDateHeure().setText(Format.format(new Date()));

            try {
                //Attente 1 secondes
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Parent.Log().PrintLN("ClockThread", "Interruption de l'horloge: " + ex);
                Thread.currentThread().interrupt();
            }
        }
    }
}
