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
    
    protected static final Properties Config;
    
    static
    {
        Config = new Properties();
        try {
            Config.load(new FileInputStream("Config.properties"));
        }
        catch (FileNotFoundException ex) {
            //A remplacer par une jdialog prenant le nom, l'ip et le port distant pour ensuite les ajouter ici.
            Config.setProperty("Ip_Serveur_CommandesLubrifiant",    "127.0.0.1");
            Config.setProperty("Port_Serveur_CommandesLubrifiant",       "3400");
            Config.setProperty("Ip_Serveur_CommandesPièces",        "127.0.0.1");
            Config.setProperty("Port_Serveur_CommandesPièces",           "3401");
            Config.setProperty("Ip_Serveur_CommandesPneux",         "127.0.0.1");
            Config.setProperty("Port_Serveur_CommandesPneux",            "3402");
            
            try {
                Config.store(new FileOutputStream("Config.properties"),"Fichier de Configuration du Programme: ");
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(Commandes.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (IOException ex1) {
                Logger.getLogger(Commandes.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(Commandes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
    
    public String Send( NetworkBasicClient c) throws java.lang.NullPointerException
    {
        return c.sendString(getTuple());
    }
}
