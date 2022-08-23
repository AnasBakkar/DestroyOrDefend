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
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public abstract class Movement {

    protected Grid grid;
    public List<Pair> acceptable_moves(int x,int y)
    {
        List<Pair> point = new ArrayList<>();
        for(int i = -1 ; i <= 1 ; i++)
            for(int j = -1 ; j <= 1 ; j++) 
            {
                int newX = x + i;
                int newY = y + j;
                if(newX < 0 || newY < 0)
                    continue;
                if(newX > 10000 || newY > 10000)
                    continue;
                if(i == 0 && j == 0) 
                    continue;
                point.add(new Pair(newX,newY));
            }
        return point;
    }
    public void moveToAnotherCell(Unit unit, int x, int y) {
        Grid grid = Grid.get_instance();
        try{ 
        grid.remove(unit);
        }catch(NullPointerException e)
        {
        }
        unit.position.centerX = x;
        unit.position.centerY = y;
        grid.addUnit(unit);
    }
    public abstract void move(Unit unit);
}
