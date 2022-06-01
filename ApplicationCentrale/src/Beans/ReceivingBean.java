/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import Beans.Events.InstockEvent;
import Beans.Events.InstockEventListener;
import Commandes.CommandesLubrifiants;
import Commandes.CommandesPièces;
import Commandes.CommandesPneux;
import java.util.StringTokenizer;
import network.NetworkBasicServer;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import Commandes.*;
import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author student
 */
public class ReceivingBean implements Serializable, InstockEventListener {
    
    //Read / Write Network
    private NetworkBasicServer Server;
    private JTextField Text;
    
    //Event
    private Commandes MyNewCommandes;
    private PropertyChangeSupport propertySupport;
    
    public ReceivingBean() {
        Server = null;
        Text = null;
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public ReceivingBean(JCheckBox Check, JTextField text) {
        Server = new NetworkBasicServer(50500, Check);
        Text = text;
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public ReceivingBean(int Port, JCheckBox Check, JTextField text) {
        if(Check == null)
            Server = null;
        else
            Server = new NetworkBasicServer(Port, Check);
        
        if(text == null)
            Text = null;
        else
            Text = text;
        
        propertySupport = new PropertyChangeSupport(this);
    }
    
    //Fonction lançant la lecture d'un message:
    public Commandes Lire(){
        if(Server == null || Text == null) return null;
        
        Commandes tmp = null;
        String t = "";
        
        String m = Server.getMessage();
        
        if(m == null || m.equals("")){
            return null;
        }
        
        if(m.equals("END")){
            Text.setText("Fin Connexion");
            Server.setEndReceiving();
            return null;
        }
            
        //Creation du Tokenizer
        StringTokenizer Message = new StringTokenizer(m,";");
        
        //Recuperation Premier Token, si il n'y en a pas plus, c'est qu'on a rien reçus (Rien)
        t = Message.nextToken();    
        if(!Message.hasMoreTokens()) return null;
        
        //Si il y en a +, alors on a reçus une nouvelle commande
        switch(t){
            case "Commandes.CommandesLubrifiants":
                tmp = new CommandesLubrifiants();
                break;
                    
            case "Commandes.CommandesPièces":
                tmp = new CommandesPièces();
                break;
                    
            case "Commandes.CommandesPneux":
                tmp = new CommandesPneux();
                break;
                    
            default:
                return null;
        }
        
        //Recuperation Libellé
        t = Message.nextToken();
        tmp.setLibellé(t);
           
        //Recuperation Type
        t = Message.nextToken();
        tmp.setType(t);
            
        //Recuperation Quantité
        t = Message.nextToken();
        tmp.setQuantité(Integer.parseInt(t));
            
        //Recuperation Prioritée
        t = Message.nextToken();
            
        switch(t){
            case "URGENT":
                tmp.setPriorité(Commandes.Priorité.URGENT);
                break;
                    
            case "NORMAL":
                tmp.setPriorité(Commandes.Priorité.NORMAL);
                break;
                
            case "PASPRIORITAIRE":
                tmp.setPriorité(Commandes.Priorité.PASPRIORITAIRE);
                break;
                
            default:
                tmp.setPriorité(Commandes.Priorité.PASPRIORITAIRE);
                break;
        }
        
        //Set la commande ce qui génèrera un PropertyChangeEvent
        setMyNewCommandes(tmp);
        
        //On retourne l'objet Commande venant d'être créer.
        return tmp;
    }
    
    //GetterCmd
    public Commandes getMyNewCommandes() {
        return MyNewCommandes;
    }
    
    //Setter + FirePropertyChange
    private void setMyNewCommandes(Commandes cmd) {
        //Ajout au TextField
        Text.setText(">>  "+cmd);
        
        //Changement de la propriété + firepropertychangeEvent
        this.MyNewCommandes = cmd;
        propertySupport.firePropertyChange("Commandes",null, MyNewCommandes);
    }
    
    
    
    //Add or Delete From Listener
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    @Override
    public void InstockEventDetected(InstockEvent ie) {
        
        String isOk;
        String m;
        
        if(ie.isDispo())
        {
            m = "OK" + ";" + ie.getCmd().toString() + ";" + ie.getDate();
        }
        else
        {
            m = "KO" + ";" + ie.getCmd().toString();
        }
        
        System.out.println(m);
        this.Server.sendMessage(m);
    }
}
