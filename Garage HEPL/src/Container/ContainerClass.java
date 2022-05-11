/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author student
 */
public class ContainerClass implements Serializable {
    
    private LinkedList ListeTravaux;
    private LinkedList ListeTravauxEnCours;
    private LinkedList ListeTravauxFini;

    public ContainerClass() 
    {
        ListeTravaux = new LinkedList();
        ListeTravauxEnCours = new LinkedList();
        ListeTravauxFini = new LinkedList();
    }
    
    public LinkedList getListeTravaux() {
        return ListeTravaux;
    }
    
    public LinkedList getListeTravauxEnCours() {
        return ListeTravauxEnCours;
    }

    public LinkedList getListeTravauxFini() {
        return ListeTravauxFini;
    }
    
    public boolean isPontFree(String p)
    {
        System.out.println(ListeTravauxEnCours);
        if(ListeTravauxEnCours.isEmpty()) return true;
        
        //Fonction permettant de vérifier qu'un pont est libre.
        for(int i = 0 ; i < ListeTravauxEnCours.size() ; i++)
        {
            Vector tmp = (Vector) ListeTravauxEnCours.get(i);
            if(((String)tmp.lastElement()).equals(p)){
                return false;
            }
        }
        return true;
    }
    
    public boolean isSolFree()
    {
        System.out.println(ListeTravauxEnCours);
        if(ListeTravauxEnCours.isEmpty()) return true;
        
        //Fonction permettant de vérifier qu'un pont est libre.
        for(int i = 0 ; i < ListeTravauxEnCours.size() ; i++)
        {
            Vector tmp = (Vector) ListeTravauxEnCours.get(i);
            if(((String)tmp.lastElement()).equals("Sol")){
                return false;
            }
        }
        return true;
    }
}
