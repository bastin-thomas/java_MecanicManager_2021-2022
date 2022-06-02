/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Vector;
import travail.Travail;


/**
 *
 * @author student
 */
public class ContainerClass implements Serializable {
    
    //Vecteur des Clients
    private Vector Clients;
    
    //Vecteur des Utilisateurs de l'application
    private Vector Users;

    //Partie Travaux
    private LinkedList ListeTravaux;
    
    Travail Pont1;
    Travail Pont2;
    Travail Pont3;
    Travail Pont4;
    Travail Sol;
    
    private LinkedList ListeTravauxFini;
    
    //Historiques des Commandes
    private Vector ListeCommandes;
    
    //Format Date:
    private Vector EtatFormat;
    private SimpleDateFormat dateformat;
    
    public ContainerClass() 
    {
        Clients = new Vector();
        Users = new Vector();
        
        ListeTravaux = new LinkedList();
        ListeTravauxFini = new LinkedList();
        
        Pont1 = null;
        Pont2 = null;
        Pont3 = null;
        Pont4 = null;
        Sol = null;
        
        ListeCommandes = new Vector();
        
        EtatFormat = new Vector();
        dateformat = new SimpleDateFormat();
    }

    public void setEtatFormat(Vector EtatFormat) {
        this.EtatFormat = EtatFormat;
    }

    public void setDateformat(SimpleDateFormat dateformat) {
        this.dateformat = dateformat;
    }

    public Vector getEtatFormat() {
        return EtatFormat;
    }

    public SimpleDateFormat getDateformat() {
        return dateformat;
    }
    
    public void setUsers(Vector Users) {
        this.Users = Users;
    }

    public Vector getUsers() {
        return Users;
    }    
    
    public LinkedList getListeTravaux() {
        return ListeTravaux;
    }
   
    public LinkedList getListeTravauxFini() {
        return ListeTravauxFini;
    }
    
    public void setListeTravaux(LinkedList ListeTravaux) {
        this.ListeTravaux = ListeTravaux;
    }
    
    public void setListeTravauxFini(LinkedList ListeTravauxFini) {
        this.ListeTravauxFini = ListeTravauxFini;
    }
    
    public void setListeCommandes(Vector ListeCommandes) {
        this.ListeCommandes = ListeCommandes;
    }

    public Vector getListeCommandes() {
        return ListeCommandes;
    }

    public Travail getPont1() {
        return Pont1;
    }

    public Travail getPont2() {
        return Pont2;
    }

    public Travail getPont3() {
        return Pont3;
    }

    public Travail getPont4() {
        return Pont4;
    }

    public Travail getSol() {
        return Sol;
    }

    public void setPont1(Travail Pont1) {
        this.Pont1 = Pont1;
    }

    public void setPont2(Travail Pont2) {
        this.Pont2 = Pont2;
    }

    public void setPont3(Travail Pont3) {
        this.Pont3 = Pont3;
    }

    public void setPont4(Travail Pont4) {
        this.Pont4 = Pont4;
    }

    public void setSol(Travail Sol) {
        this.Sol = Sol;
    }
    
    public boolean IsPont1Free(){
        if(Pont1 == null) return true;
        else return false;
    }    
    
    public boolean IsPont2Free(){
        if(Pont2 == null) return true;
        else return false;
    } 
    
    public boolean IsPont3Free(){
        if(Pont3 == null) return true;
        else return false;
    } 
    
    public boolean IsPont4Free(){
        if(Pont4 == null) return true;
        else return false;
    } 
    
    public boolean IsSolFree(){
        if(Sol == null) return true;
        else return false;
    } 

    /**
     * @return the Clients
     */
    public Vector getClients() {
        return Clients;
    }

    /**
     * @param Clients the Clients to set
     */
    public void setClients(Vector Clients) {
        this.Clients = Clients;
    }
}
