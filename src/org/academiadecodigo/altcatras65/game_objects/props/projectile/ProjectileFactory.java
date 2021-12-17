package org.academiadecodigo.altcatras65.game_objects.props.projectile;

import org.academiadecodigo.altcatras65.util.GameSprites;
import org.academiadecodigo.altcatras65.grid.Grid;

public class ProjectileFactory {

    public static Projectile createWeapon(int col, int row, Grid grid) {
        return new Mouse(ProjectileType.MOUSE.getTravelDistance(),
                ProjectileType.MOUSE.getDamage(),
                grid.makeGridPosition(col, row, GameSprites.PLAYER),
                ProjectileType.MOUSE, grid);
    }
}
