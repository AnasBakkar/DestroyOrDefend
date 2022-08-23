/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author hp
 */
public class RandomAttackStrategy implements AttackStrategy{

    @Override
    public Unit PrioritizeUnitToAttack(List<Unit> Enemies) {
        Random r = new Random(); 
        int i = r.nextInt(Enemies.size());
        return Enemies.get(i);
    }
}
