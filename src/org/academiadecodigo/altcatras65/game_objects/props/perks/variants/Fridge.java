package org.academiadecodigo.altcatras65.game_objects.props.perks.variants;

import org.academiadecodigo.altcatras65.game_objects.alive.player.Player;
import org.academiadecodigo.altcatras65.game_objects.props.perks.Perk;
import org.academiadecodigo.altcatras65.grid.Grid;
import org.academiadecodigo.altcatras65.grid.gridposition.GridPosition;

public class Fridge extends Perk {

    public Fridge(int scoreCost, int restoreHp, int restoreArmor, GridPosition position, Grid grid) {
        super(scoreCost, restoreHp, restoreArmor, position, grid);
    }

    @Override
    public boolean consume(Player player) {
        if (player.getScore() - super.getScoreCost() >= 0) {
            player.setHp(super.getRestoreHP());
            player.setScore(player.getScore()-super.getScoreCost());
            System.out.println("Player HP restored: +" + super.getRestoreHP());
            return true;
        } else {
            System.out.println("Player Score: " + player.getScore() + " Score Needed: " + getScoreCost() + " Not enough score to interact");
            return false;
        }
    }
}
