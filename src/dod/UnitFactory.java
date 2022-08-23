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
public class UnitFactory {
    public Unit CreateUnit(UnitType unitType,Player player,int x,int y,int id)
    {
        Unit unit = new Unit(unitType,player,x,y,id);
        return unit;
}
}
