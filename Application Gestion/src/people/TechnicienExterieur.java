/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;
import authenticate.AValider;
import java.util.HashMap;

/**
 *
 * @author student
 */
public class TechnicienExterieur extends Personne implements AValider
{
        
    private String _matricule;
    private boolean _validation;

    /**
     * Get the value of _matricule
     *
     * @return the value of _matricule
     */
    public String getMatricule() {
        return _matricule;
    }

    /**
     * Set the value of _matricule
     *
     * @param _matricule new value of _matricule
     */
    public void setMatricule(String _matricule) {
        this._matricule = _matricule;
    }

    @Override
    public String getId() {
        return getMatricule();
    }

    @Override
    public void setId(String id) {
        setMatricule(id);
    }
    
    @Override
    public boolean isValid() 
    {
        if(_validation == true) return true;
        else return false;
    }

    @Override
    public boolean validate() {
        _validation = true;
        return true;
    }

    public TechnicienExterieur() {
        super();
        this._matricule = "NaN";
        this._validation = false;
    }
    
    public TechnicienExterieur(String _matricule) {
        super();
        this._matricule = _matricule;
        this._validation = false;
    }

    public TechnicienExterieur(String _matricule, String _nom, String _prenom, String _adresse, String _numtel) {
        super(_nom, _prenom, _adresse, _numtel);
        this._matricule = _matricule;
        this._validation = false;
    }

    public TechnicienExterieur(String _matricule, String _nom, String _prenom) {
        super(_nom, _prenom);
        this._matricule = _matricule;
        this._validation = false;
    }

    @Override
    public String toString() {
        return "TechnicienExterieur{" + "_matricule=" + _matricule + ", _nom=" + _nom + ", _prenom=" + _prenom + ", _adresse=" + _adresse + ", _numtel=" + _numtel +'}';
    }
    
    public static void main(String[] args) {

        TechnicienExterieur c1 = new TechnicienExterieur();
        TechnicienExterieur c2 = new TechnicienExterieur("MAT0001","Arnone","Matteo");
        
        System.out.print("\n\nc1:\n" + c1);
        System.out.print("\n\nc2:\n" + c2);
    }
}
