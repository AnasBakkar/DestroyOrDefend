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
public class IllegalBuy extends Exception{
    String message;

    public IllegalBuy(String message) {
        this.message = message;
    }
    @Override
    public String toString(){
        return ("An Exception Occurred : " + message);
    }
}
