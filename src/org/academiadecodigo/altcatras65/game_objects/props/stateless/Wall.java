package org.academiadecodigo.altcatras65.game_objects.props.stateless;

import org.academiadecodigo.altcatras65.game_objects.props.Prop;
import org.academiadecodigo.altcatras65.grid.Grid;
import org.academiadecodigo.altcatras65.grid.gridposition.GridPosition;

public class Wall extends Prop {

    public Wall(GridPosition position, Grid grid) {
        super(position,grid);
    }
}
