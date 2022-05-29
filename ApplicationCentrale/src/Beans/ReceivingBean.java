/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Commandes.Commandes;
import Commandes.CommandesLubrifiants;
import Commandes.CommandesPièces;
import Commandes.CommandesPneux;
import java.util.StringTokenizer;
import network.NetworkBasicServer;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author student
 */
public class ReceivingBean {
    private NetworkBasicServer Server;

    public ReceivingBean(int Port, JCheckBox Check) {
        Server = new NetworkBasicServer(Port, Check);
        
    }
    
    public Commandes Lire(JTextField Text){
        Commandes tmp = null;


        String m = Server.getMessage();
        if(m == null || m.equals("")){
            return null;
        } 
            
        StringTokenizer Message = new StringTokenizer(m,";");
            
        String t = "";
        t = Message.nextToken();
            
        if(!Message.hasMoreTokens()) return null;
        
            
        switch(t){
            case "Commandes.CommandesLubrifiants":
                tmp = new CommandesLubrifiants();
                break;
                    
            case "Commandes.CommandesPièces":
                tmp = new CommandesPièces();
                break;
                    
            case "Commandes.CommandesPneux":
                tmp = new CommandesPneux();
                break;
                    
            default:
                return null;
        }
            
        t = Message.nextToken();
        tmp.setLibellé(t);
            
        t = Message.nextToken();
        tmp.setType(t);
            
        t = Message.nextToken();
        tmp.setQuantité(Integer.parseInt(t));
            
        t = Message.nextToken();
            
        switch(t){
            case "URGENT":
                tmp.setPriorité(Commandes.Priorité.URGENT);
                break;
                    
            case "NORMAL":
                tmp.setPriorité(Commandes.Priorité.NORMAL);
                break;
                
            case "PASPRIORITAIRE":
                tmp.setPriorité(Commandes.Priorité.PASPRIORITAIRE);
                break;
                
            default:
                tmp.setPriorité(Commandes.Priorité.PASPRIORITAIRE);
                break;
        }
        
        //Ajout au TextField
        Text.setText(">>  "+tmp);
        
        return tmp;
    }
    
    
}
