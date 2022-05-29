/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travail;

import people.Mecanicien;
import vehicules.Voiture;

/**
 *
 * @author student
 */
public class Entretien extends Travail
{

    public Entretien() {
    }

    public Entretien(Voiture vehicule) {
        super(vehicule);
    }

    public Entretien(Mecanicien mecano, Voiture vehicule) {
        super(mecano, vehicule);
    }
    
}
