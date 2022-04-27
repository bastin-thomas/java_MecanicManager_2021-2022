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
public class Employe extends PersonnelGarage
{

    public Employe() {
        super();
    }

    public Employe(String _matricule) {
        super(_matricule);
    }

    public Employe(String _matricule, String _nom, String _prenom, String _adresse, String _numtel) {
        super(_matricule, _nom, _prenom, _adresse, _numtel);
    }

    public Employe(String _matricule, String _nom, String _prenom) {
        super(_matricule, _nom, _prenom);
    }

    @Override
    public String toString() {
        return "Employe{" + "_matricule=" + _matricule + ", _nom=" + _nom + ", _prenom=" + _prenom + ", _adresse=" + _adresse + ", _numtel=" + _numtel + '}';
    }
    
    public static void main(String[] args) {

        Employe c1 = new Employe();
        Employe c2 = new Employe("MAT0001","Arnone","Matteo");
        
        System.out.print("\n\nc1:\n" + c1);
        System.out.print("\n\nc2:\n" + c2);
    }
}
