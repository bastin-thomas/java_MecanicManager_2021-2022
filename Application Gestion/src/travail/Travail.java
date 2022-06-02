/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travail;
import java.io.Serializable;
import java.util.Vector;
import people.Mecanicien;
import vehicules.*;
/**
 *
 * @author student
 */
public abstract class Travail implements Serializable {
    
    protected Mecanicien mecano;
    protected Voiture vehicule;
    protected String TypeTravail; 
    protected String Message;
    
    public Travail() {
        this.mecano = null;
        this.vehicule = null;
    }
    
    public Travail(Voiture vehicule) {
        this.vehicule = vehicule;
    }  
    
    public Travail(Mecanicien mecano, Voiture vehicule) {
        this.mecano = mecano;
        this.vehicule = vehicule;
    }   

    public Travail(Mecanicien mecano, Voiture vehicule, String Type) {
        this.mecano = mecano;
        this.vehicule = vehicule;
        this.TypeTravail = Type;
    }
    
    public Travail(Mecanicien mecano, Voiture vehicule, String Type, String Message) {
        this.mecano = mecano;
        this.vehicule = vehicule;
        this.TypeTravail = Type;
        this.Message = Message;
    }
    
    /**
     * Get the value of vehicule
     *
     * @return the value of vehicule
     */
    public Vehicule getVehicule() {
        return vehicule;
    }

    /**
     * Set the value of vehicule
     *
     * @param vehicule new value of vehicule
     */
    public void setVehicule(Voiture vehicule) {
        this.vehicule = vehicule;
    }
    
    /**
     * Get the value of mecano
     *
     * @return the value of mecano
     */
    public Mecanicien getMecano() {
        return mecano;
    }

    /**
     * Set the value of mecano
     *
     * @param mecano new value of mecano
     */
    public void setMecano(Mecanicien mecano) {
        this.mecano = mecano;
    }
    
    public String getTypeTravail() {
        return TypeTravail;
    }

    public void setTypeTravail(String Type) {
        this.TypeTravail = Type;
    }
    
    public void setMessage(String Message) {
        this.Message = Message;
    }
    
    public String getMessage(){
        return Message;
    }
    
    public Vector toVector(){
        Vector tmp = new Vector();
        
        tmp.add(vehicule.getType().toString());
        tmp.add(vehicule.getPlaque());
        tmp.add(vehicule.getClient().getNom());
        tmp.add(TypeTravail);
        tmp.add(Message);
        
        return tmp;
    }

    @Override
    public String toString() {
        return " "+vehicule.getType().toString()+" | "+vehicule.getPlaque()+" |  "+TypeTravail;
    }    
}
