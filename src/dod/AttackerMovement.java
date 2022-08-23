/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dod;

import java.io.IOException;
import java.util.Collections;
import static java.util.Collections.sort;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author hp
 */
public class AttackerMovement extends Movement {

    static AttackerMovement attackerMovement;

    private AttackerMovement() {
        
    }

    public static AttackerMovement get_instance() { 
        if (attackerMovement == null) { 
            attackerMovement = new AttackerMovement();
        }
        return attackerMovement;
    }

    Double computeDistanceFromBase(Pair point1) {
        Unit mainBase = Grid.get_mainBase();
        int x = mainBase.position.centerX;
        int y = mainBase.position.centerY;
        double z;
        z = Math.sqrt(((x - (int) point1.getKey())*(x - (int) point1.getKey())) + ((y - (int) point1.getValue())*(y - (int) point1.getValue())));
        return (Double) z;
    }

    @Override
    public void move(Unit unit) {
        Grid grid = Grid.get_instance();
        List<Pair> points = (List<Pair>) acceptable_moves(unit.position.centerX,unit.position.centerY);
        if((unit.unitType == UnitType.Airplane) && (unit.isCharged != true)){
            sort(points, new Comparator<Pair>() { 
            @Override
            public int compare(Pair lhs, Pair rhs) { 
                return computeDistanceFromOrigin(lhs,unit).compareTo(computeDistanceFromOrigin(rhs,unit));
            }
        });
        }
        else{
        sort(points, new Comparator<Pair>() { 
            @Override
            public int compare(Pair lhs, Pair rhs) { 
                return computeDistanceFromBase(lhs).compareTo(computeDistanceFromBase(rhs));
            }
        });
        }
        for (Pair point : points) {
            if (grid.AcceptUnitMovement(unit, (int) point.getKey(), (int) point.getValue())) { //System.out.println("here");
                    unit.position.centerX = (int) point.getKey();
                    unit.position.centerY = (int) point.getValue();
                    try {
                        unit.logFile.write(String.valueOf(unit.position.centerX) + " , ");
                        unit.logFile.write(String.valueOf(unit.position.centerY) + "\n");
                    } catch (IOException ex) {
                    }
                    break;
                }
            else if (grid.acceptMoveToAnother(unit, (int) point.getKey(), (int) point.getValue())) {
                    moveToAnotherCell(unit, (int) point.getKey(), (int) point.getValue());
                    try {
                        unit.logFile.write(String.valueOf(unit.position.centerX) + " , ");
                        unit.logFile.write(String.valueOf(unit.position.centerY) + "\n");
                    } catch (IOException ex) {
                    }
                    break;
            }
        }
        if(unit.unitType == UnitType.Airplane) 
        {
            Unit mainBase = Grid.get_mainBase();
            int distanceFromBase = (int)Math.sqrt(((mainBase.position.centerX - unit.position.centerX)*(mainBase.position.centerX - unit.position.centerX)) + ((mainBase.position.centerY - unit.position.centerY)*(mainBase.position.centerY - unit.position.centerY)));
            int distanceFromOrigin = (int)Math.sqrt(((unit.OriginX - unit.position.centerX)*(unit.OriginX - unit.position.centerX)) + ((unit.OriginY - unit.position.centerY)*(unit.OriginY - unit.position.centerY)));
            if(distanceFromOrigin == 1){ 
                unit.isCharged = true;
            } 
            else if(distanceFromBase == unit.getAttackRange() - 1){ 
                unit.isCharged = false;
            }
        }
        
    }
    
    Double computeDistanceFromOrigin(Pair point1,Unit unit) {
        int x = unit.OriginX;
        int y = unit.OriginY;
        double z;
        z = Math.sqrt(((x - (int) point1.getKey())*(x - (int) point1.getKey())) + ((y - (int) point1.getValue())*(y - (int) point1.getValue())));
        return (Double) z;
    }
}