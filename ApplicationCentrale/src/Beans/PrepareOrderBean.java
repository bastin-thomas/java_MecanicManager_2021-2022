/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Beans.Events.InstockEvent;
import Beans.Events.InstockEventListener;
import Beans.Events.SearchFoundEvent;
import Beans.Events.SearchFoundEventListener;
import Commandes.Commandes;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author student
 */
public class PrepareOrderBean implements Serializable, SearchFoundEventListener {
    
    private ArrayList<InstockEventListener> InstockEventListenerList;
    
    public PrepareOrderBean(){
        InstockEventListenerList = new ArrayList<InstockEventListener>();
    }
    
    
    @Override
    public void SearchFoundEventDetected(SearchFoundEvent sfe) {
        //Traitement lors de l'event
        System.out.println("PrepareOrderBean: SearchFoundEvent reçus.");
        
        LocalDate date;
        int n;
        Random r = new Random();
        
        if(sfe.isDispo()){
            n = r.nextInt(9) + 1;
            //Nombre alléatoire de 10 jours ajouter a la date.
            date = LocalDate.now().plusDays(n);
        }
        else date = null;
        
        //Lancer l'event Instock
        notifyInstockEvent(sfe.getCmd(), sfe.isDispo(), date);
    }
    
    public void notifyInstockEvent(Commandes c, boolean dispo, LocalDate date){
        InstockEvent e = new InstockEvent(c, dispo, date);
        for(InstockEventListener li:InstockEventListenerList)
            li.InstockEventDetected(e);   
    }
    
    public void addInstockEventListener(InstockEventListener listener){
        InstockEventListenerList.add(listener);
    }
    
    public void removeInstockEventListener(InstockEventListener listener){
        InstockEventListenerList.remove(listener);
    }
}
