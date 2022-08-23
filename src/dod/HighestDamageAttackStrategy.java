/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dod;

import java.util.List;

/**
 *
 * @author hp
 */
public class HighestDamageAttackStrategy implements AttackStrategy{

    public static HighestDamageAttackStrategy highestDamageAttackStrategy;
    private HighestDamageAttackStrategy() {

    }

    public static HighestDamageAttackStrategy get_instance() {
        if (highestDamageAttackStrategy == null) {
            highestDamageAttackStrategy = new HighestDamageAttackStrategy();
        }
        return highestDamageAttackStrategy;
    }
    @Override
    public Unit PrioritizeUnitToAttack(List<Unit> Enemies) {
        Unit maxDamageUnit = Enemies.get(0);
        for(int i = 1 ; i < Enemies.size() ; i++){
            if (maxDamageUnit.damage < Enemies.get(i).damage) { 
                maxDamageUnit = Enemies.get(i);
            }
            }
        return maxDamageUnit;
    }
}
