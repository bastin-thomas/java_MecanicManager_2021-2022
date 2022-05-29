/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicules;
import java.io.Serializable;
import people.Client;

/**
 *
 * @author student
 */
public class Voiture extends Vehicule implements Serializable
{
    
    private Client _client;
    private TypeVoiture _type;

    /**
     * Get the value of _type
     *
     * @return the value of _type
     */
    public TypeVoiture getType() {
        return _type;
    }

    /**
     * Set the value of _type
     *
     * @param _type new value of _type
     */
    public void setType(TypeVoiture _type) {
        this._type = _type;
    }

    
    /**
     * Get the value of client
     *
     * @return the value of client
     */
    public Client getClient() 
    {
        return _client;
    }

    /**
     * Set the value of client
     *
     * @param client new value of client
     */
    public void setClient(Client client) 
    {
        this._client = client;
    }
    
    
    public Voiture() {
        super();
    }

    public Voiture(Client _client, TypeVoiture _type) {
        super();
        this._client = _client;
        this._type = _type;
    }

    public Voiture(Client _client, TypeVoiture _type, String _plaque) {
        super(_plaque);
        this._client = _client;
        this._type = _type;
    }

    @Override
    public String toString() {
        return "Voiture{" + "_client=" + _client + ", _type=" + _type + ", _plaque=" + _plaque + '}';
    }    
}
