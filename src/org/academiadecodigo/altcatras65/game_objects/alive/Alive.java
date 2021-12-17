package org.academiadecodigo.altcatras65.game_objects.alive;

import org.academiadecodigo.altcatras65.game_objects.GameObject;
import org.academiadecodigo.altcatras65.grid.Grid;
import org.academiadecodigo.altcatras65.grid.gridposition.GridDirectionType;
import org.academiadecodigo.altcatras65.grid.gridposition.GridPosition;

public abstract class Alive extends GameObject implements Killable{
    private int hp;
    private int armor;
    private boolean isAlive;


    public Alive(int hp, int armor , GridPosition position, Grid grid) {
        super(position, grid);
        this.hp = hp;
        this.armor = armor;
        this.isAlive = true;
    }

    public int getHp() {
        return hp;
    }

    public int getArmor() {
        return armor;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public abstract void move(GridDirectionType direction);
}
