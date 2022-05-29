/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;
import authenticate.Identifiable;
import java.io.Serializable;

/**
 *
 * @author student
 */
public abstract class Personne implements Identifiable, Serializable
{
    protected String _nom;
    protected String _prenom;
    protected String _adresse;
    protected String _numtel;

    public String getNom() {
        return _nom;
    }

    public void setNom(String _nom) {
        this._nom = _nom;
    }

    public String getPrenom() {
        return _prenom;
    }

    public void setPrenom(String _prenom) {
        this._prenom = _prenom;
    }

    public String getAdresse() {
        return _adresse;
    }

    public void setAdresse(String _adresse) {
        this._adresse = _adresse;
    }

    public String getNumtel() {
        return _numtel;
    }

    public void setNumtel(String _numtel) {
        this._numtel = _numtel;
    }

    public Personne() {
        this._nom = "NaN";
        this._prenom = "NaN";
        this._adresse = "NaN";
        this._numtel = "NaN";
    }

    public Personne(String _nom, String _prenom, String _adresse, String _numtel) {
        this._nom = _nom;
        this._prenom = _prenom;
        this._adresse = _adresse;
        this._numtel = _numtel;
    }

    public Personne(String _nom, String _prenom) {
        this._nom = _nom;
        this._prenom = _prenom;
        this._adresse = "NaN";
        this._numtel = "NaN";
    }

    @Override
    public String toString() {
        return "Personne{" + "_nom=" + _nom + ", _prenom=" + _prenom + ", _adresse=" + _adresse + ", _numtel=" + _numtel + '}';
    }
}
