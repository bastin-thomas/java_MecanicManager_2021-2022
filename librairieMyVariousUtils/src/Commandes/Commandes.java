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
    
    
    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param Type new value of type
     */
    public void setType(String Type) {
        this.type = Type;
    }
    
    /**
     * Get the value of libellé
     *
     * @return the value of libellé
     */
    public String getLibellé() {
        return libellé;
    }

    /**
     * Set the value of libellé
     *
     * @param Libellé new value of libellé
     */
    public void setLibellé(String Libellé) {
        this.libellé = Libellé;
    }
    
    /**
     * Get the value of quantité
     *
     * @return the value of quantité
     */
    public int getQuantité() {
        return quantité;
    }

    /**
     * Set the value of quantité
     *
     * @param Quantité new value of quantité
     */
    public void setQuantité(int Quantité) {
        this.quantité = Quantité;
    }
    
    /**
     * Get the value of Priorité
     *
     * @return the value of Priorité
     */
    public Priorité getPriorité() {
        return priorité;
    }

    /**
     * Set the value of Priorité
     *
     * @param Priorité new value of Priorité
     */
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
