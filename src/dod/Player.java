/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dod;

import static dod.DoDGameManager.doDGameManager;

/**
 *
 * @author hp
 */
public class Player {

    protected int teamId;
    public int coins = 3000;
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    boolean isAttacker() {
        if (getTeamId() == 1) {
            return true;
        } else {
            return false;
        }
    }
}
