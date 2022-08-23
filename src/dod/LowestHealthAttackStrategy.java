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
public class LowestHealthAttackStrategy implements AttackStrategy{

    public static LowestHealthAttackStrategy lowestHealthAttackStrategy;
    private LowestHealthAttackStrategy() {

    }

    public static LowestHealthAttackStrategy get_instance() {
        if (lowestHealthAttackStrategy == null) {
            lowestHealthAttackStrategy = new LowestHealthAttackStrategy();
        }
        return lowestHealthAttackStrategy;
    }
    @Override
    public Unit PrioritizeUnitToAttack(List<Unit> Enemies) {
        Unit minHealthUnit = Enemies.get(0);
          for(int i = 1 ; i < Enemies.size() ; i++){ 
            if (minHealthUnit.health > Enemies.get(i).health) {
                minHealthUnit = Enemies.get(i);
            }
        }
        return minHealthUnit;
    }
    
    
}
