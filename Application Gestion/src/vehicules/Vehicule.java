/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicules;

import java.io.Serializable;

/**
 *
 * @author student
 */
public abstract class Vehicule implements Serializable {
    
    protected String _plaque;

    /**
     * Get the value of _plaque
     *
     * @return the value of _plaque
     */
    public String getPlaque() {
        return _plaque;
    }

    /**
     * Set the value of _plaque
     *
     * @param _plaque new value of _plaque
     */
    public void setPlaque(String _plaque) {
        this._plaque = _plaque;
    }

    public Vehicule() {
        this._plaque = "NaN";
    }
    
    public Vehicule(String _plaque) {
        this._plaque = _plaque;
    }
}
