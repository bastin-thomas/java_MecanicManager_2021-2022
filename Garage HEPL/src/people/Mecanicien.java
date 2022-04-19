/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;

/**
 *
 * @author student
 */
public class Mecanicien extends PersonnelGarage
{
    
    private String Specialite;

    /**
     * Get the value of Specialite
     *
     * @return the value of Specialite
     */
    public String getSpecialite() {
        return Specialite;
    }

    /**
     * Set the value of Specialite
     *
     * @param Specialite new value of Specialite
     */
    
    
    public void setSpecialite(String Specialite) {
        this.Specialite = Specialite;
    }

    public Mecanicien() {
        super();
        this.Specialite = "NaN";
    }
    
    public Mecanicien(String Specialite) {
        super();
        this.Specialite = Specialite;
    }

    public Mecanicien(String Specialite, String _matricule) {
        super(_matricule);
        this.Specialite = Specialite;
    }

    public Mecanicien(String Specialite, String _matricule, String _nom, String _prenom, String _adresse, String _numtel) {
        super(_matricule, _nom, _prenom, _adresse, _numtel);
        this.Specialite = Specialite;
    }

    public Mecanicien(String Specialite, String _matricule, String _nom, String _prenom) {
        super(_matricule, _nom, _prenom);
        this.Specialite = Specialite;
    }
    
    public Mecanicien(String _matricule, String _nom, String _prenom, String _adresse, String _numtel) {
        super(_matricule, _nom, _prenom, _adresse, _numtel);
        this.Specialite = "NaN";
    }

    public Mecanicien(String _matricule, String _nom, String _prenom) {
        super(_matricule, _nom, _prenom);
        this.Specialite = "NaN";
    }

    @Override
    public String toString() {
        return "Mecanicien{" + "_matricule=" + _matricule + "Specialite=" + Specialite + ", _nom=" + _nom + ", _prenom=" + _prenom + ", _adresse=" + _adresse + ", _numtel=" + _numtel + '}';
    }
}
