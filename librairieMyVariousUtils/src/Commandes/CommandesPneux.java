/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commandes;

import java.util.Properties;
import network.*;

/**
 *
 * @author student
 */
public class CommandesPneux extends Commandes{

    public CommandesPneux() {
    }

    /**
     *
     * @return
     */
    public static int loadPort(Properties Config) {
        int Port;
        
        try{
            Port = Integer.parseInt( Config.getProperty("Port_Serveur_CommandesPneux", ""));
        } catch(NumberFormatException ex){
          Port = 50502;
          Config.setProperty("Port_Serveur_CommandesPneux", String.valueOf(Port));
        }
        
        if(Port < 50000 || Port >= 65000){
            Port = 50502;
            Config.setProperty("Port_Serveur_CommandesPneux", String.valueOf(Port));
        }
        
        return Port;
    }

    public static NetworkBasicClient CreateClient(Properties Config){
        
        String Adresse = Config.getProperty("Ip_Serveur_CommandesPneux","");
        if(Adresse.equals("")){
            Adresse = "127.0.0.1";
        }
        

        //Tentative Creation du Serveur
        NetworkBasicClient tmp = new NetworkBasicClient(Adresse,loadPort(Config));
        
        System.out.toString();
        
        if(System.out.toString().contains("java.net.ConnectException")){
            return null;
        }
        return tmp;
    }
}
