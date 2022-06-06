/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commandes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.NetworkBasicClient;

/**
 *
 * @author student
 */

public abstract class Commandes implements Serializable {
    
    public enum Priorité {URGENT, NORMAL, PASPRIORITAIRE};
    
    private String type;
    private String libellé;
    private int quantité;
    private Priorité priorité;
    
    

    public String getType() {
        return type;
    }


    public void setType(String Type) {
        this.type = Type;
    }
    

    public String getLibellé() {
        return libellé;
    }


    public void setLibellé(String Libellé) {
        this.libellé = Libellé;
    }
    

    public int getQuantité() {
        return quantité;
    }


    public void setQuantité(int Quantité) {
        this.quantité = Quantité;
    }
    

    public Priorité getPriorité() {
        return priorité;
    }


    public void setPriorité(Priorité prior) {
        this.priorité = prior;
    }
    
    @Override
    public String toString() {
        return "Libell\u00e9: " + libellé + ",   Type: " + type + ",   Quantit\u00e9: " + quantité;
    }
    
    public String getTuple(){
        return this.getClass().getName() + ";" + libellé + ";" + type + ";" + quantité + ";" + priorité ;
    }
    
    
    public String Send( NetworkBasicClient c) 
    {
        String r;
        try{
            r = c.sendString(getTuple());
        }
        catch(java.lang.NullPointerException npe){
            r = null;
        }
        
        return r;
    }
}
