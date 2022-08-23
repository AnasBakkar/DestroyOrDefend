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
public class DefenderTeam extends Team{
    static DefenderTeam defenderTeam;

    private DefenderTeam() {
        
    }

    public static DefenderTeam get_instance() {
        if (defenderTeam == null) {
            defenderTeam = new DefenderTeam();
        }
        return defenderTeam;
    }
}
