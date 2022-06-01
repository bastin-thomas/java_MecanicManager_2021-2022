/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Beans.Events.SearchFoundEvent;
import Beans.Events.SearchFoundEventListener;
import Commandes.Commandes;
import applicationcentrale.ApplicationCentrale;
import applicationcentrale.ContainerAppCentrale;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author student
 */
public class SearchBean implements PropertyChangeListener, Serializable {
    
    private ArrayList<SearchFoundEventListener> SearchFoundListenerList;
    
    private ContainerAppCentrale Container;
    private JComboBox combo;
    private String FilePath;
    private Commandes Cmd;
    
    public SearchBean(String fp, JComboBox c){
        if(fp == null) fp = "default.ser";
        FilePath = fp;
        SearchFoundListenerList = new ArrayList<SearchFoundEventListener>();
        combo = c;
        
        LoadContainer(FilePath);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Cmd = (Commandes) evt.getNewValue();
        Container.getListeCommande().add(Cmd);
        RefreshUI();
        
        SaveContainer(FilePath);
    }
    
    public void Reponse(boolean dispo){   
        if(Cmd == null) return;
        
        notifySearchFoundEvent(Cmd,dispo);
    }
    
    public void notifySearchFoundEvent(Commandes c, boolean dispo){
        SearchFoundEvent e = new SearchFoundEvent(c, dispo);
        for(SearchFoundEventListener li:SearchFoundListenerList)
            li.SearchFoundEventDetected(e);   
    }
    
    public void addSearchFoundEventListener(SearchFoundEventListener listener){
        SearchFoundListenerList.add(listener);
    }
    
    public void removeSearchFoundEventListener(SearchFoundEventListener listener){
        SearchFoundListenerList.remove(listener);
    }
    
    private void RefreshUI(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) combo.getModel();
        //Supprime les anciens Row
        model.removeAllElements();
        
        for(int i = 0 ; i < Container.getListeCommande().size() ; i++)
        {
            model.addElement( Container.getListeCommande().get(i));
        }
        combo.setModel(model);
    }
    
    
    public void LoadContainer(String FilePath){
        try {
            //Chargement des flux:
            ObjectInputStream ObjectIN = new ObjectInputStream(new FileInputStream(FilePath));
            
            //Recuperation du Container
            setContainer((ContainerAppCentrale) ObjectIN.readObject());
            ObjectIN.close();
            
        } catch (FileNotFoundException ex) {
            
            //Si il n'y a rien à charger, On met en mémoire ses données par défaut
            ContainerAppCentrale tmp = new ContainerAppCentrale();
            setContainer(tmp);
            
        } catch (IOException | ClassNotFoundException ex) {
        }
    }
    
    public void SaveContainer(String FilePath){
        //Chargement de l'état du programme a son ouverture :
        try {
            //Chargement des Flux:
            ObjectOutputStream ObjectOUT = new ObjectOutputStream(new FileOutputStream(FilePath));
            
            //Enregistrement en Fichier
            ObjectOUT.writeObject(getContainer());
            ObjectOUT.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ApplicationCentrale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @return the Container
     */
    public ContainerAppCentrale getContainer() {
        return Container;
    }

    /**
     * @param Container the Container to set
     */
    public void setContainer(ContainerAppCentrale Container) {
        this.Container = Container;
    }
    
}
