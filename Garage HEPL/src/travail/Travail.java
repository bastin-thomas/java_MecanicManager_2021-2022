/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travail;
import people.Mecanicien;
import vehicules.*;
/**
 *
 * @author student
 */
public abstract class Travail {
    
    protected Mecanicien mecano;
    protected Vehicule vehicule;

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
    public void setVehicule(Vehicule vehicule) {
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
    
    public Travail() {
        this.mecano = null;
        this.vehicule = null;
    }
    
    public Travail(Mecanicien mecano, Vehicule vehicule) {
        this.mecano = mecano;
        this.vehicule = vehicule;
    }   
}
