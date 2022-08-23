/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dod;

import java.io.IOException;

public class UnitAttack {

    protected UnitAttack unitAttack;

    public boolean attackable(Unit targetedUnit, Unit Attacker) {
        for (UnitType canAttack : Attacker.canAttack) {
            if (targetedUnit.unitType == canAttack) {
                return true;
            }
        }
        return false;
    }

    public void PerformAttack(Unit targetedUnit, Unit Attacker) {
        targetedUnit.AcceptDamage(Attacker.damage);
        try {
                        Attacker.logFile.write(String.valueOf(Attacker.position.centerX) + " , ");
                        Attacker.logFile.write(String.valueOf(Attacker.position.centerY)+ "  ");
                        Attacker.logFile.write((String.valueOf(Attacker.id)) + "\n");
                    } catch (IOException ex) { 
                        //Logger.getLogger(DefenderMovement.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
}
