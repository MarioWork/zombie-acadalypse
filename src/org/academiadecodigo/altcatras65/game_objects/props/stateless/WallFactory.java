package org.academiadecodigo.altcatras65.game_objects.props.stateless;

import org.academiadecodigo.altcatras65.util.GameSprites;
import org.academiadecodigo.altcatras65.grid.Grid;

public class WallFactory {

    public static Wall createWall(Grid grid, int col, int row, int width, int height) {
        return new Wall(grid.makeGridPosition(width, height, col, row, GameSprites.WALL), grid);
    }
}
