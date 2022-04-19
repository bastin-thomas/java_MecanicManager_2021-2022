/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicules;
/**
 *
 * @author student
 */
public class TypeVoiture 
{
    private String _marque;
    private String _type;
    private int _nbPortes;

    /**
     * Get the value of _nbPortes
     *
     * @return the value of _nbPortes
     */
    public int getNbPortes() 
    {
        return _nbPortes;
    }

    /**
     * Set the value of _nbPortes
     *
     * @param _nbPortes new value of _nbPortes
     */
    public void setNbPortes(int _nbPortes) 
    {
        this._nbPortes = _nbPortes;
    }

    /**
     * Get the value of _type
     *
     * @return the value of _type
     */
    public String getType() 
    {
        return _type;
    }

    /**
     * Set the value of _type
     *
     * @param _type new value of _type
     */
    public void setType(String _type) 
    {
        this._type = _type;
    }

    /**
     * Get the value of _marque
     *
     * @return the value of _marque
     */
    public String getMarque() 
    {
        return _marque;
    }

    /**
     * Set the value of _marque
     *
     * @param _marque new value of _marque
     */
    public void setMarque(String _marque) 
    {
        this._marque = _marque;
    }

    public TypeVoiture(String _marque, String _type, int _nbPortes) throws MissingTradeMarkException
    {
        if(_marque == null) throw new MissingTradeMarkException("La marque ne peut pas être null.\n");
        
        this._marque = _marque;
        this._type = _type;
        this._nbPortes = _nbPortes;
    }
    
    @Override
    public String toString() 
    {
        return "TypeVoiture{" + "_marque=" + _marque + ", _type=" + _type + ", _nbPortes=" + _nbPortes + '}';
    }
}
