package org.academiadecodigo.altcatras65.game_objects.props.perks;

import org.academiadecodigo.altcatras65.game_objects.props.Prop;
import org.academiadecodigo.altcatras65.grid.Grid;
import org.academiadecodigo.altcatras65.grid.gridposition.GridPosition;

public abstract class Perk extends Prop implements Interactable {
    private int scoreCost;
    private int restoreArmor;
    private int restoreHP;

    public Perk(int scoreCost, int restoreHP, int restoreArmor, GridPosition position, Grid grid) {
        super(position, grid);
        this.scoreCost = scoreCost;
        this.restoreHP = restoreHP;
        this.restoreArmor = restoreArmor;
    }

    public int getRestoreHP() {
        return restoreHP;
    }

    public int getRestoreArmor() {
        return restoreArmor;
    }

    public int getScoreCost() {
        return scoreCost;
    }


}
