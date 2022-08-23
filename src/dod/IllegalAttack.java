/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dod;

/**
 *
 * @author hp
 */
public class IllegalAttack extends Exception{
    String message;

    public IllegalAttack(String message) {
        this.message = message;
    }
    public String toString(){
        return ("An Exception Occurred : " + message);
    }
    
}
