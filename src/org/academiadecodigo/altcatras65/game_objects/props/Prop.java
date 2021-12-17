package org.academiadecodigo.altcatras65.game_objects.props;

import org.academiadecodigo.altcatras65.game_objects.GameObject;
import org.academiadecodigo.altcatras65.grid.Grid;
import org.academiadecodigo.altcatras65.grid.gridposition.GridPosition;

public abstract class Prop extends GameObject {

    public Prop(GridPosition position, Grid grid) {
        super(position, grid);
    }
}
