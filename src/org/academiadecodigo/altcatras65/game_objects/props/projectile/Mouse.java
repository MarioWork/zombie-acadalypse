package org.academiadecodigo.altcatras65.game_objects.props.projectile;

import org.academiadecodigo.altcatras65.util.GameSprites;
import org.academiadecodigo.altcatras65.grid.Grid;
import org.academiadecodigo.altcatras65.grid.gridposition.GridDirectionType;
import org.academiadecodigo.altcatras65.grid.gridposition.GridPosition;

public class Mouse extends Projectile {

    public Mouse(int travelDistance, int damage, GridPosition position, ProjectileType projectileType, Grid grid) {
        super(travelDistance, damage, position, projectileType, grid);
    }

    @Override
    public void mThrow(int distance, GridDirectionType directionType) {
        super.getPos().moveInDirection(directionType, distance, GameSprites.BALL);
    }
}

