/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commandes;

import static Commandes.CommandesPneux.loadPort;
import network.*;

/**
 *
 * @author student
 */
public class CommandesPièces extends Commandes {

    public CommandesPièces() {
    }

    /**
     *
     * @return
     */
    public static int loadPort() {
        int Port;
        
        try{
            Port = Integer.parseInt( Config.getProperty("Port_Serveur_CommandesPièces", ""));
        } catch(NumberFormatException ex){
          Port = 50501;
          Config.setProperty("Port_Serveur_CommandesPièces", String.valueOf(Port));
        }
        
        if(Port < 50000 || Port >= 65000){
            Port = 50501;
            Config.setProperty("Port_Serveur_CommandesPièces", String.valueOf(Port));
        }
        
        return Port;
    }

    
    public static NetworkBasicClient CreateClient(){
        
        String Adresse = Config.getProperty("Ip_Serveur_CommandesPièces","");
        if(Adresse.equals("")){
            Adresse = "127.0.0.1";
        }
        
        
        //Tentative Creation du Serveur
        NetworkBasicClient tmp = new NetworkBasicClient(Adresse,loadPort());
        
        System.out.toString();
        
        if(System.out.toString().contains("java.net.ConnectException")){
            return null;
        }
        return tmp;
    }
}
