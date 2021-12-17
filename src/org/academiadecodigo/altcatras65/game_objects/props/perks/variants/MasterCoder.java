package org.academiadecodigo.altcatras65.game_objects.props.perks.variants;

import org.academiadecodigo.altcatras65.game_objects.alive.player.Player;
import org.academiadecodigo.altcatras65.game_objects.props.perks.Perk;
import org.academiadecodigo.altcatras65.grid.Grid;
import org.academiadecodigo.altcatras65.grid.gridposition.GridPosition;

public class MasterCoder extends Perk {

    public MasterCoder(int scoreCost, int restoreHP, int restoreArmor, GridPosition position, Grid grid) {
        super(scoreCost, restoreHP, restoreArmor, position, grid);
    }

    @Override
    public boolean consume(Player player) {
        if (player.getScore() - super.getScoreCost() >= 0) {
            player.setArmor(super.getRestoreArmor());
            player.setScore(player.getScore()-super.getScoreCost());
            System.out.println("Player ARMOR restored: +" + super.getRestoreArmor());
            return true;
        } else {
            System.out.println("Player Score: " + player.getScore() + " Score Needed: " + getScoreCost() + " Not enough score to interact");
            return false;
        }
    }
}
