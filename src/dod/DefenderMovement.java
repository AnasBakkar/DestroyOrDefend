/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dod;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author hp
 */
public class DefenderMovement extends Movement {

    static DefenderMovement defenderMovement;

    private DefenderMovement() {
        grid = Grid.get_instance();

    }

    public static DefenderMovement get_instance() {
        if (defenderMovement == null) {
            defenderMovement = new DefenderMovement();
        }
        return defenderMovement;
    }

    @Override
    public void move(Unit unit) {
        List<Pair> points = (List<Pair>) acceptable_moves(unit.position.centerX, unit.position.centerY);
        for (Pair point : points) {
            if (grid.AcceptUnitMovement(unit, (int) point.getKey(), (int) point.getValue())) {
                if (Math.sqrt((((int) point.getKey() - unit.OriginX) * ((int) point.getKey() - unit.OriginX)) + (((int) point.getValue() - unit.OriginY) * ((int) point.getValue() - unit.OriginY))) < unit.position.radius) {
                    int x = (int) Math.sqrt((((int) point.getKey() - unit.OriginX) * ((int) point.getKey() - unit.OriginX)) + (((int) point.getValue() - unit.OriginY) * ((int) point.getValue() - unit.OriginY)));
                    int y = unit.position.radius;
                    if (x == y - 1) {
                        unit.position.centerX = unit.OriginX;
                        unit.position.centerY = unit.OriginY;
                    } else {
                        unit.position.centerX = (int) point.getKey();
                        unit.position.centerY = (int) point.getValue();
                    }
                    try {
                        unit.logFile.write(String.valueOf(unit.position.centerX) + " , ");
                        unit.logFile.write(String.valueOf(unit.position.centerY) + "\n");
                    } catch (IOException ex) {
                        //Logger.getLogger(DefenderMovement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }
        }
    }
}
