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
    
    private LinkedList Liste_Travaux;
    private LinkedList Liste_Travaux_EnCours;
    private LinkedList Liste_Travaux_Fini;

    public ContainerClass() 
    {
        Liste_Travaux = new LinkedList();
        Liste_Travaux_EnCours = new LinkedList();
        Liste_Travaux_Fini = new LinkedList();
    }
    
    public LinkedList getList_Travaux() {
        return Liste_Travaux;
    }
    
    public LinkedList getList_Travaux_EnCours() {
        return Liste_Travaux_EnCours;
    }

    public LinkedList getList_Travaux_Fini() {
        return Liste_Travaux_Fini;
    }
    
    public boolean isPontFree(String p)
    {
        System.out.println(Liste_Travaux_EnCours);
        if(Liste_Travaux_EnCours.isEmpty()) return true;
        
        //Fonction permettant de vérifier qu'un pont est libre.
        for(int i = 0 ; i < Liste_Travaux_EnCours.size() ; i++)
        {
            Vector tmp = (Vector) Liste_Travaux_EnCours.get(i);
            if(((String)tmp.lastElement()).equals(p)){
                return false;
            }
        }
        return true;
    }
    
    public boolean isSolFree()
    {
        System.out.println(Liste_Travaux_EnCours);
        if(Liste_Travaux_EnCours.isEmpty()) return true;
        
        //Fonction permettant de vérifier qu'un pont est libre.
        for(int i = 0 ; i < Liste_Travaux_EnCours.size() ; i++)
        {
            Vector tmp = (Vector) Liste_Travaux_EnCours.get(i);
            if(((String)tmp.lastElement()).equals("Sol")){
                return false;
            }
        }
        return true;
    }
}
