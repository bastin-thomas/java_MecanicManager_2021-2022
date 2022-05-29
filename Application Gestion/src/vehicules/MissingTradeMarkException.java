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
public class MissingTradeMarkException extends Exception {

    /**
     * Creates a new instance of <code>MissingTradMarkException</code> without
     * detail message.
     */
    public MissingTradeMarkException() {
    }

    /**
     * Constructs an instance of <code>MissingTradMarkException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public MissingTradeMarkException(String msg) {
        super(msg);
    }
}
