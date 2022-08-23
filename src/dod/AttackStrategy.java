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
public interface AttackStrategy {
    abstract Unit PrioritizeUnitToAttack(List<Unit> Enemies);
}
