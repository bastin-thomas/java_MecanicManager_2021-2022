/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationcentrale;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author student
 */
public class ContainerAppCentrale implements Serializable {
    private Vector ListeCommande;
    
    public ContainerAppCentrale(){
        ListeCommande = new Vector();
    }

    /**
     * @return the ListeCommande
     */
    public Vector getListeCommande() {
        return ListeCommande;
    }

    /**
     * @param ListeCommande the ListeCommande to set
     */
    public void setListeCommande(Vector ListeCommande) {
        this.ListeCommande = ListeCommande;
    }
    
    
}
