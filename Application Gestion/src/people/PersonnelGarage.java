/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;
import authenticate.AValider;

/**
 *
 * @author student
 */
public abstract class PersonnelGarage extends Personne implements AValider
{    
    
    
    protected String _matricule;
    protected boolean _validation;

    public String getMatricule() {
        return _matricule;
    }

    public void setMatricule(String _matricule) {
        this._matricule = _matricule;
    }

    public PersonnelGarage() {
        this._validation = false;
        this._matricule = "NaN";
    }

    public PersonnelGarage(String _matricule) {
        super();
        this._validation = false;
        this._matricule = _matricule;
    }

    public PersonnelGarage(String _matricule, String _nom, String _prenom, String _adresse, String _numtel) {
        super(_nom, _prenom, _adresse, _numtel);
        this._validation = false;
        this._matricule = _matricule;
    }

    public PersonnelGarage(String _matricule, String _nom, String _prenom) {
        super(_nom, _prenom);
        this._validation = false;
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
        if(_validation == true){
            _validation = false;
            return true;
        }
        else{
            _validation = true;
            return true;
        }
    }
}
