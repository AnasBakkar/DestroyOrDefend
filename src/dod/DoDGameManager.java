/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dod;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author hp
 */
enum GameState {
    Running,
    Paused,
    AttackerWon,
    DefenderWon
}

public class DoDGameManager implements Runnable {

    public static DoDGameManager doDGameManager;

    private DoDGameManager() {
        remainigTime = 0;
    }

    public static DoDGameManager get_instance() {
        if (doDGameManager == null) {
            doDGameManager = new DoDGameManager();
        }
        return doDGameManager;
    }
    public static int AttackerTeamCount;
    public static int DefenderTeamCount;
    public Unit mainBase;
    int remainigTime;
    UnitFactory unitFactory = new UnitFactory();
    Grid grid = Grid.get_instance();
    Team team[];
    public boolean isReady;
    static long start;
    long end;
    
    public Unit buyUnit(UnitType unitType, int x, int y, Player player, int id) {
            Unit unit = unitFactory.CreateUnit(unitType, player, x, y, id);
            if(unit.getPrice() < player.coins)
            {
            grid.addUnit(unit);
            player.coins = player.coins - unit.getPrice();
            }
            return unit;
        }

    public boolean isIsReady() {
        return isReady;
    }

    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }
    public void initialPlan(int id){
        Grid grid = Grid.get_instance();
        Unit temp = null;
        for(int i = 0 ; i <= 2 ; i++)
            for(int j = 0 ; j <= 2 ; j++)
            {
            temp = grid.allUnits[i][j];
            while(temp != null){
                if(temp.id == id)
                    break;
                temp = temp._next;
            } 
            } 
        temp.bolber = true;
    }
    public boolean isGameFinished() {
        GameState gameState = null;
        end = System.currentTimeMillis();
        remainigTime = (int) (end - start);
        return remainigTime == 5 && gameState == GameState.Paused;
    }

    public static void main(String[] args) {
        DoDGameManager doDGameManager1 = DoDGameManager.get_instance();
        Thread thread = new Thread(doDGameManager1);
        thread.start();
    }

    @Override
    public void run() {
        Player player = new Player();
        start = System.currentTimeMillis();
        while (!isGameFinished()) { 
            Grid grid1 = Grid.get_instance();
            int choice;
            Scanner input = new Scanner(System.in);
            do {
                System.out.println("Choose what you want to do:");
                System.out.println("1- to create new unit");
                System.out.println("2- to let the created units start moving");
                System.out.println("3- to print the positions of the created units");
                System.out.println("4- to print a detailed log for each unit");
                System.out.println("5- to let the battle start");
                choice = input.nextInt();
                switch (choice) { 
                    case 1:
                        int Idcount = 1;
                        int x,y;
                        int z;
                        Scanner inp = new Scanner(System.in);
                        System.out.println("Enter the coordinates of the unit you want to buy");
                        x = inp.nextInt();
                        y = inp.nextInt();
                        System.out.println("Enter 1 if it's attacking Unit , 2 if it's Defending");
                        z = inp.nextInt();
                        player.setTeamId(z);
                        int type;
                        Unit unit;
                        Thread thread;
                        System.out.println("1 for NavySeal \n 2 for GrizzlyTank \n 3 for PrismTank \n 4 for Pillbox\n");
                        type = inp.nextInt();
                        switch (type) {
                            case 1:
                                unit = unitFactory.CreateUnit(UnitType.NavySeal, player, x, y, Idcount);
                                thread = new Thread(unit);
                                thread.start();
                                Idcount++;
                                break;
                            case 2:
                                unit = unitFactory.CreateUnit(UnitType.GrizzlyTank, player, x, y, Idcount);
                                thread = new Thread(unit);
                                thread.start();
                                Idcount++;
                                break;
                            case 3:
                                unit = unitFactory.CreateUnit(UnitType.PrismTank, player, x, y, Idcount);
                                thread = new Thread(unit);
                                thread.start();
                                Idcount++;
                                break;
                            case 4:
                                unit = unitFactory.CreateUnit(UnitType.Pillbox, player, x, y, Idcount);
                                thread = new Thread(unit);
                                thread.start();
                                Idcount++;
                                break;
                        } 
                        break;
                    case 2:
                        player.setTeamId(2);
//                        unit = buyUnit(UnitType.PrismTank,500,950, player, 4);
//                        thread = new Thread(unit);
//                        thread.start();
//                        unit = buyUnit(UnitType.PatriotMissileSystem, 400, 400, player, 7);
//                        thread = new Thread(unit);
//                        thread.start();
//                        unit = buyUnit(UnitType.GrandCannon,500,500, player, 4);
//                        thread = new Thread(unit);
//                        thread.start();
                          player.setTeamId(1);
                          unit = buyUnit(UnitType.NavySeal,10,2, player, 5);
                          thread = new Thread(unit); 
                          thread.start(); 
                        unit = buyUnit(UnitType.Airplane,3,2, player, 6);
                        thread = new Thread(unit);
                        thread.start();
//                        unit = buyUnit(UnitType.GrizzlyTank,100,150, player, 8);
//                        thread = new Thread(unit);
//                        thread.start();
//                        unit = buyUnit(UnitType.Infantry,1500,1300, player, 9);
//                        thread = new Thread(unit);
//                        thread.start();
//                        unit = buyUnit(UnitType.Sniper,200,400, player, 10);
//                        thread = new Thread(unit);
//                        thread.start();
//                        unit = buyUnit(UnitType.TeslaTank,600,500, player, 11);
//                        thread = new Thread(unit);
//                        thread.start();
//                        unit = buyUnit(UnitType.PrismTank,960,940, player, 12);
//                        thread = new Thread(unit);
//                        thread.start();
//                        unit = buyUnit(UnitType.Airplane,3,9, player, 5);
//                        thread = new Thread(unit);
//                        thread.start();
//                        unit = buyUnit(UnitType.Infantry,3,2, player, 5);
//                        thread = new Thread(unit);
//                        thread.start();
//                        unit = buyUnit(UnitType.TeslaTank, 500, 500, player, 4);
//                        thread = new Thread(unit);
//                        thread.start();
                        break;
                    case 3:
                        mainBase = Grid.get_mainBase();
                        grid1.query(1, 0);
                        grid1.query(0, 0);
                        grid1.query(0, 1);
                        grid1.query(1, 1);
                        System.out.printf("mainBase health is : %d\n", mainBase.health);
                        break;
                    case 4:
                        Scanner i = new Scanner(System.in);
                        int id;
                        id = i.nextInt();
                        try{
                        FileReader reader = new FileReader(String.valueOf(id));
                        int c;
                        while((c = reader.read())!= -1)
                        {
                            System.out.print((char)c);
                        }
                            System.out.print("\n");
                        reader.close();
                        }catch(IOException e){} 
                        break;
                    case 5:
                        //setIsReady(true);
                        break;
                    case 6:
                        Scanner input1 = new Scanner(System.in);
                        int d = input1.nextInt();
                        initialPlan(d);
                        break;
                    default:
                        System.out.println("wrong choice! Enter again");
                }
            } while (choice != 0);
        }
    }
}
