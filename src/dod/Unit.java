/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dod;

import java.io.FileWriter;
import java.io.IOException;
//import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


enum UnitType {
    Pillbox,
    Airplane,
    river,
    valley,
    Bridge,
    mainBase,
    Sniper,
    Infantry,
    GrizzlyTank,
    NavySeal,
    TeslaTank,
    MirageTank,
    TankDestroyer,
    PrismTank,
    PrismTower,
    GrandCannon,
    BlackEagle,
    PatriotMissileSystem
}

public class Unit implements Runnable {

    double armor;
    int price;
    Unit _next;
    Unit _prev;
    UnitType canAttack[];
    Movement movement;
    UnitPosition position;
    Unit targetedUnit;
    UnitType unitType;
    int AttackRange;
    Player owner;
    AttackStrategy attackStrategy;
    DoDGameManager doDGameManager;
    public int health;
    public int damage;
    Grid grid;
    int MovementSpeed;
    double AttackFrequency;
    int id;
    public int OriginX;
    public int OriginY;
    public FileWriter logFile;
    public boolean isCharged = true;
    public boolean bolber = false;

    public double getAttackFrequency() {
        return AttackFrequency;
    }

    public void setAttackFrequency(double AttackFrequency) {
        this.AttackFrequency = AttackFrequency;
    }

    public int getMovementSpeed() {
        return MovementSpeed;
    }

    public void setMovementSpeed(int MovementSpeed) {
        this.MovementSpeed = MovementSpeed;
    }

    public UnitPosition getPosition() {
        return position;
    }

    public void setPosition(UnitPosition position) {
        this.position = position;
    }

    public Unit(UnitType unitType, Player owner, int x, int y, int id) {
        OriginX = x;
        OriginY = y;
        position = new UnitPosition();
        position.centerX = x;
        position.centerY = y;
        grid = Grid.get_instance();
        this.id = id;
        this.unitType = unitType;
        this.owner = owner;
        if (owner.isAttacker()) {
            movement = AttackerMovement.get_instance();
            attackStrategy = LowestHealthAttackStrategy.get_instance();
        } else {
            movement = DefenderMovement.get_instance();
        }
        intializeUnitValus(unitType);
        doDGameManager = DoDGameManager.get_instance();

    }

    public int getAttackRange() {
        return AttackRange;
    }

    public void setAttackRange(int AttackRange) {
        this.AttackRange = AttackRange;
    }

    public void AcceptDamage(int Attackerdamage) {
        this.health = this.health - Attackerdamage;
    }


    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    boolean isDestroyed() {
        return health <= 0;
    }

    private void intializeUnitValus(UnitType unitType) {
        if (unitType == UnitType.mainBase) {
            position.radius = 30;
            health = 10000;
            damage = 75;
            armor = 0;
            setAttackRange(0);
            setAttackFrequency(0);
            setMovementSpeed(0);
            setPrice(0);
        }
        if (unitType == UnitType.river) {
            position.radius = 50;
            health = 0;
            damage = 0;
            armor = 0;
            setAttackRange(0);
            setAttackFrequency(0);
            setMovementSpeed(0);
            setPrice(0);
        }
        if (unitType == UnitType.valley) {
            position.radius = 50;
            health = 0;
            damage = 0;
            armor = 0;
            setAttackRange(0);
            setAttackFrequency(0);
            setMovementSpeed(0);
            setPrice(0);
        }
        ////////////// SOLIDERS  //////////////
        if (unitType == UnitType.Sniper) {
            canAttack = new UnitType[]{UnitType.NavySeal, UnitType.Infantry, UnitType.Sniper , UnitType.mainBase};
            health = 250;
            damage = 75;
            armor = 0.10;
            setAttackRange(700);
            setAttackFrequency(0.4);
            position.radius = 0;
            setMovementSpeed(3);
            setPrice(5);
        }

        if (unitType == UnitType.Infantry) {
            canAttack = new UnitType[]{UnitType.NavySeal, UnitType.Infantry, UnitType.Sniper , UnitType.mainBase};
            health = 250;
            damage = 50;
            armor = 0.20;
            setAttackRange(50);
            setAttackFrequency(1.5);
            position.radius = 3;
            setMovementSpeed(10);
            setPrice(3);
        }

        if (unitType == UnitType.NavySeal) {
            canAttack = new UnitType[]{UnitType.NavySeal, UnitType.Infantry, UnitType.Sniper, UnitType.Airplane, UnitType.GrandCannon, UnitType.GrizzlyTank, UnitType.MirageTank, UnitType.PatriotMissileSystem, UnitType.PrismTank, UnitType.PrismTower, UnitType.TankDestroyer, UnitType.TeslaTank, UnitType.mainBase, UnitType.Pillbox};
            health = 400;
            damage = 60;
            armor = 0.20;
            setAttackRange(50);
            setAttackFrequency(2);
            position.radius = 3;
            setMovementSpeed(20);
            setPrice(50);
        }
        /////////////// TANKS /////////////////////
        if (unitType == UnitType.MirageTank) {
            canAttack = new UnitType[]{UnitType.NavySeal, UnitType.Infantry, UnitType.Sniper, UnitType.Airplane, UnitType.GrandCannon, UnitType.GrizzlyTank, UnitType.MirageTank, UnitType.PatriotMissileSystem, UnitType.PrismTank, UnitType.PrismTower, UnitType.TankDestroyer, UnitType.TeslaTank, UnitType.mainBase, UnitType.Pillbox};
            health = 750;
            damage = 1000;
            armor = 0.10;
            setAttackRange(10);
            setAttackFrequency(1);
            position.radius = 15;
            setMovementSpeed(60);
            setPrice(5);
        }

        if (unitType == UnitType.GrizzlyTank) {
            canAttack = new UnitType[]{UnitType.NavySeal, UnitType.Infantry, UnitType.Sniper, UnitType.Airplane, UnitType.GrandCannon, UnitType.GrizzlyTank, UnitType.MirageTank, UnitType.PatriotMissileSystem, UnitType.PrismTank, UnitType.PrismTower, UnitType.TankDestroyer, UnitType.TeslaTank, UnitType.mainBase, UnitType.Pillbox};
            health = 1000;
            damage = 250;
            armor = 0.40;
            setAttackRange(250);
            setAttackFrequency(0.60);
            position.radius = 20;
            setMovementSpeed(30);
            setPrice(50);
        }
        if (unitType == UnitType.PrismTank) {
            canAttack = new UnitType[]{UnitType.NavySeal, UnitType.Infantry, UnitType.Sniper, UnitType.Airplane, UnitType.GrandCannon, UnitType.GrizzlyTank, UnitType.MirageTank, UnitType.PatriotMissileSystem, UnitType.PrismTank, UnitType.PrismTower, UnitType.TankDestroyer, UnitType.TeslaTank, UnitType.mainBase, UnitType.Pillbox};
            health = 1000;
            damage = 300;
            armor = 0.35;
            setAttackRange(150);
            setAttackFrequency(0.60);
            position.radius = 20;
            setMovementSpeed(20);
            setPrice(60);
        }
        if (unitType == UnitType.TankDestroyer) {
            canAttack = new UnitType[]{UnitType.MirageTank, UnitType.PrismTank, UnitType.TankDestroyer, UnitType.GrizzlyTank,UnitType.mainBase};
            health = 1000;
            damage = 400;
            armor = 0.50;
            setAttackRange(150);
            setAttackFrequency(0.60);
            position.radius = 20;
            setMovementSpeed(20);
            setPrice(50);
        }
        /////////// TOWERS /////////////////

        if (unitType == UnitType.Pillbox) {
            canAttack = new UnitType[]{UnitType.NavySeal, UnitType.Infantry, UnitType.Sniper};
            health = 4000;
            damage = 100;
            armor = 0.70;
            setAttackRange(200);
            setAttackFrequency(0.70);
            position.radius = 40;
            setMovementSpeed(0);
            setPrice(150);
        }
        if (unitType == UnitType.PrismTower) {
            canAttack = new UnitType[]{UnitType.NavySeal, UnitType.Infantry, UnitType.Sniper, UnitType.MirageTank, UnitType.PrismTank, UnitType.TankDestroyer, UnitType.GrizzlyTank};
            health = 4000;
            damage = 100;
            armor = 0.70;
            setAttackRange(200);
            setAttackFrequency(0.50);
            position.radius = 30;
            setMovementSpeed(0);
            setPrice(150);
        }
        if (unitType == UnitType.GrandCannon) {
            canAttack = new UnitType[]{UnitType.NavySeal, UnitType.Infantry, UnitType.Sniper, UnitType.MirageTank, UnitType.PrismTank, UnitType.TankDestroyer, UnitType.GrizzlyTank};
            health = 6500;
            damage = 150;
            armor = 0.30;
            setAttackRange(400);
            setAttackFrequency(0.90);
            position.radius = 40;
            setMovementSpeed(0);
            setPrice(200);
        }
        if (unitType == UnitType.PatriotMissileSystem) {
            canAttack = new UnitType[]{UnitType.Airplane};
            health = 2500;
            damage = 50;
            armor = 0.20;
            setAttackRange(400);
            setAttackFrequency(0.40);
            position.radius = 40;
            setMovementSpeed(0);
            setPrice(175);
        }
        ///////////// AIRPLANE ///////////////////////
        if (unitType == UnitType.Airplane) {
            canAttack = new UnitType[]{UnitType.mainBase};
            health = 1500;
            damage = 400;
            armor = 0.00;
            setAttackRange(30);
            setMovementSpeed(100);
            setPrice(75);
        }
    }

    boolean isReady() {
        doDGameManager = DoDGameManager.get_instance();
        if (doDGameManager.isReady) {
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        UnitAttack unitAttack = new UnitAttack();
        Unit mainBase = Grid.get_mainBase();
        Unit river = Grid.get_river();
        while (!doDGameManager.isGameFinished() && !isDestroyed() && !mainBase.isDestroyed()) {
//            if(!isReady())
//                continue;
            /*if(this.bolber = true){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
                }
                bolber = false;
            }*/
            try {
                logFile = new FileWriter(String.valueOf(id), true);
            } catch (IOException ex) {
                Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
            }
            movement.move(this);
            try {
                double distance = Math.sqrt(((this.position.centerX - river.position.centerX) * (this.position.centerX - river.position.centerX)) + ((this.position.centerY - river.position.centerY) * (this.position.centerY - river.position.centerY)));
                if (distance < river.position.radius || distance == 0) {
                    Thread.sleep((1000 / getMovementSpeed()) * 4);
                } else {
                    Thread.sleep(1000 / getMovementSpeed());
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ArithmeticException e) {
            }
            /*targetedUnit =  attackStrategy.PrioritizeUnitToAttack(Enemies);*/
            List Enemies;
            Enemies = grid.GetAllUnitsInRange(this);
            try {
                targetedUnit = (Unit) Enemies.get(0);
            } catch (IndexOutOfBoundsException e) {
            }
            /*try{
            if (!unitAttack.attackable(targetedUnit, this)) {
                        targetedUnit = null;
                    }
            }catch(NullPointerException x){}*/
            try {
                while (!targetedUnit.isDestroyed()) {
                    System.out.println("targeted");
                    unitAttack.PerformAttack(targetedUnit, this);
                    if (unitType == UnitType.Airplane) {
                        targetedUnit = null;
                    } 
                    try {
                        Thread.sleep((long) (getAttackFrequency() * 1000));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (NullPointerException r) {
            }
            
            }
            try {
                logFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
            } catch(NullPointerException t){}
            if (this.isDestroyed()) {
                System.out.println("I'm done");
                grid.remove(this);
                if (this.owner.getTeamId() == 1) {
                    DoDGameManager.AttackerTeamCount--;
                } else if (this.owner.getTeamId() == 2) {
                    DoDGameManager.DefenderTeamCount--;
                }
        }
    }

    public UnitType[] getCanAttack() {
        return canAttack;
    }

    public void setCanAttack(UnitType[] canAttack) {
        this.canAttack = canAttack;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }
}
