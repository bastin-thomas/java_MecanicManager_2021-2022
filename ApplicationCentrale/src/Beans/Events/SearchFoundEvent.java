/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans.Events;

import Commandes.Commandes;

/**
 *
 * @author student
 */
public class SearchFoundEvent {
    
    private Commandes cmd;
    private boolean dispo;
    
    public SearchFoundEvent(Commandes commande, boolean disponnibilité){
        setCmd(commande);
        setDispo(disponnibilité);
    }
    
    public Commandes getCmd() {
        return cmd;
    }    
    
    public void setCmd(Commandes c){
        cmd = c;
    }

    /**
     * @return the dispo
     */
    public boolean isDispo() {
        return dispo;
    }

    /**
     * @param dispo the dispo to set
     */
    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }
}
