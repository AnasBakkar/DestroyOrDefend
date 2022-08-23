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
public class AttackerTeam extends Team{
    public static AttackerTeam attackerTeam;
    private AttackerTeam() {

    }

    public static AttackerTeam get_instance() {
        if (attackerTeam == null) {
            attackerTeam = new AttackerTeam();
        }
        return attackerTeam;
    }
}