package dod;

import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hp
 */
public class Grid {

    public final static int NUM_CELLS = 10;
    public static int CELL_SIZE = 1000;
    public Unit allUnits[][];  //allUnits is the linked list and the Unit is the node
    private static Grid grid;
    static Player player = new Player();
    private static Unit mainBase;
    private static Unit river;

    private Grid() {
        allUnits = new Unit[CELL_SIZE][CELL_SIZE];
        
    } 
    public static Grid get_instance() {
        if (grid == null) {
            grid = new Grid();
        }
        
        return grid;
    }
    public static Unit get_mainBase()
    {
        Grid grid = Grid.get_instance();
        DoDGameManager doDGameManager = DoDGameManager.get_instance();
        if(mainBase == null){
            player.setTeamId(2);
            mainBase = doDGameManager.unitFactory.CreateUnit(UnitType.mainBase, player, 800, 800, 0);
            grid.addUnit(mainBase);
        }
        return mainBase;
    }
    public static Unit get_river(){
        Grid grid = Grid.get_instance();
        DoDGameManager doDGameManager = DoDGameManager.get_instance();
        if(river == null){
            player.setTeamId(2);
            river = doDGameManager.unitFactory.CreateUnit(UnitType.river, player, 600, 600,10);
            grid.addUnit(river);
        }
        return river;
    }
    public boolean checkPlacement(Unit unit) { 
        int x = unit.position.centerX;
        int y = unit.position.centerY;
        int cellX = (int) (x / CELL_SIZE);
        int cellY = (int) (y / CELL_SIZE);
        Unit temp = allUnits[cellX][cellY];
        if(unit.unitType == UnitType.Airplane)
            return true;
        else{
        while (temp != null) {
            float distance = (float) Math.sqrt(((temp.position.centerX - x) * (temp.position.centerX - x)) + ((temp.position.centerY - y)) * (temp.position.centerY - y));
            int d = unit.position.radius + temp.position.radius;
            if (distance < d) { 
                return false;
            }
            temp = temp._next;
        }
        }
        return true;

    }

    public void addUnit(Unit unit) {
        //Determine which grid cell it's in. 
        int cellX = (int) (unit.position.centerX / CELL_SIZE);
        int cellY = (int) (unit.position.centerY / CELL_SIZE);
        // Add to the front of list for the cell it's in. 
        boolean check = checkPlacement(unit);
        if (check) {
            unit._prev = null;
            unit._next = allUnits[cellX][cellY];
            allUnits[cellX][cellY] = unit;
            if (unit._next != null) {
                unit._next._prev = unit;
            } 
        } else {
            System.out.println("this place is already taken");
        }
    }
    
    public boolean acceptMoveToAnother(Unit unit, int x, int y) {
        int newcellX = (int) (x / CELL_SIZE);
        int newcellY = (int) (y / CELL_SIZE);
        Unit unit2 = allUnits[newcellX][newcellY];
        while (unit2 != null) { 
            float distance = (float) Math.sqrt(((unit.position.centerX - x) * (unit.position.centerX - x)) + ((unit.position.centerY - y)) * (unit.position.centerY - y));
            int d = unit.position.radius;
            if(unit2.id != unit.id){
            if (distance < d) { 
                return false; 
            }
            }
            unit2 = unit2._next;
        }
        return true; 
    }

    public boolean AcceptUnitMovement(Unit unit, int Nx, int Ny) {
        int cellX = (int) (unit.position.centerX / CELL_SIZE);
        int cellY = (int) (unit.position.centerY / CELL_SIZE);
        int newcellX = (int) (Nx / CELL_SIZE);
        int newcellY = (int) (Ny / CELL_SIZE);
        if(unit.unitType == UnitType.Pillbox || unit.unitType == UnitType.PrismTower || unit.unitType == UnitType.GrandCannon || unit.unitType == UnitType.PatriotMissileSystem)
            return false;
        if(unit.unitType == UnitType.Airplane)
            return true;
        else{ 
        if (cellX == newcellX && cellY == newcellY) {
            Unit unit1 = allUnits[cellX][cellY];
            
            while (unit1 != null) {
                if(unit1.unitType == UnitType.river || unit1.unitType == UnitType.Bridge){
                    unit1 = unit1._next;
                    continue;
                }
                float distance = (float) Math.sqrt(((unit1.position.centerX - Nx) * (unit1.position.centerX - Nx)) + ((unit1.position.centerY - Ny)) * (unit1.position.centerY - Ny));
                int d = unit.position.radius ;
                if(unit1.id != unit.id)
                {
                if (distance <= d) { 
                    return false;
                } 
                }
                unit1 = unit1._next;
            }
            return true;
        }
        return false;
    }
    }
    public void remove(Unit unit) {
        int cellX = (int) (unit.position.centerX / CELL_SIZE);
        int cellY = (int) (unit.position.centerY / CELL_SIZE);
        if (unit._prev == null) {
            allUnits[cellX][cellY] = unit._next;
            unit._next._prev = null;
            // save the head of the LL
        } else if (unit._next == null) {
            unit._prev._next = null;
        } else {
            unit._prev._next = unit._next;
        }
        unit._next._prev = unit._prev;
        unit._next = null;
    }

    public List<Unit> GetAllUnitsInRange(Unit unit) {
        List<Unit> Enemies = new LinkedList();
        int cellX = (int) (unit.position.centerX / CELL_SIZE);
        int cellY = (int) (unit.position.centerY / CELL_SIZE);
        float distance;
        Unit temp = allUnits[cellX][cellY];
        while (temp != null) {
            distance = (float) Math.sqrt(((temp.position.centerX - unit.position.centerX) * (temp.position.centerX - unit.position.centerX)) + ((temp.position.centerY - unit.position.centerY) * (temp.position.centerY - unit.position.centerY)));
            if (((distance <= unit.getAttackRange()) || (distance == 0)) && (unit.owner.getTeamId() != temp.owner.getTeamId()) ) {
                if(temp.unitType == UnitType.river){
                    temp = temp._next;
                    continue;
                }
                Enemies.add(temp);
                //System.out.println(String.valueOf(temp.unitType));
            }
            temp = temp._next;
        }
        return Enemies;
    }

    public void query(int x,int y) {
        Unit temp = allUnits[x][y];
        while (temp != null) {
            System.out.printf("there is a (%s) at position (%d,%d)\n",String.valueOf(temp.unitType),temp.position.centerX,temp.position.centerY);
            temp = temp._next;
        }
    }
}