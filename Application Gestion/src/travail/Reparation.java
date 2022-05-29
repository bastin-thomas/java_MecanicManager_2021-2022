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
public class Reparation extends Travail
{

    public Reparation() {
    }

    public Reparation(Voiture vehicule) {
        super(vehicule);
    }

    public Reparation(Mecanicien mecano, Voiture vehicule) {
        super(mecano, vehicule);
    }
    
}
