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
public class Client extends Personne
{
    private int _numClient;

    /**
     * Get the value of _numClient
     *
     * @return the value of _numClient
     */
    public int getNumClient() {
        return _numClient;
    }

    /**
     * Set the value of _numClient
     *
     * @param numClient new value of _numClient
     */
    public void setNumClient(int numClient) {
        this._numClient = numClient;
    }

    public Client() {
        this._numClient = 0;
    }

    public Client(int numClient) {
        super();
        this._numClient = numClient;
    }

    public Client(int numClient, String _nom, String _prenom, String _adresse, String _numtel) {
        super(_nom, _prenom, _adresse, _numtel);
        this._numClient = numClient;
    }

    public Client(int numClient, String _nom, String _prenom) {
        super(_nom, _prenom);
        this._numClient = numClient;
    }
    
    public Client(String _nom, String _prenom, String _adresse, String _numtel) {
        super(_nom, _prenom, _adresse, _numtel);
        this._numClient = 0;
    }

    public Client(String _nom, String _prenom) {
        super(_nom, _prenom);
        this._numClient = 0;
    }

    @Override
    public String toString() {
        return "Client{" + "numClient=" + _numClient + ", _nom=" + _nom + ", _prenom=" + _prenom + ", _adresse=" + _adresse + ", _numtel=" + _numtel +"}";
    }

    @Override
    public String getId() {
        return String.valueOf(getNumClient());
    }

    @Override
    public void setId(String id) {
        setNumClient(Integer.parseInt(id));
    }
}
