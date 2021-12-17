package org.academiadecodigo.altcatras65.game_objects;

import org.academiadecodigo.altcatras65.util.CollisionDetector;
import org.academiadecodigo.altcatras65.grid.Grid;
import org.academiadecodigo.altcatras65.grid.gridposition.GridPosition;

public abstract class GameObject {
    private CollisionDetector collisionDetector;
    private GridPosition position;
    private Grid grid;

    public GameObject( GridPosition position, Grid grid) {
        this.position = position;
        this.grid = grid;
    }

    /**
     * Gets the position of the current GameObject
     *
     * @return
     */
    public GridPosition getPos(){
        return this.position;
    }

    public void setPosition(GridPosition position) {
        this.position = position;
    }

    /**
     * Gets the grid of the current GameObject
     *
     * @return
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Returns the CollisionDetector of the current GameObject
     * @return
     */
    public CollisionDetector getCollisionDetector() {
        return collisionDetector;
    }

    /**
     * Sets the collisionDetector to the detector from the parameter
     * @param collisionDetector
     */
    public void setCollisionDetector(CollisionDetector collisionDetector) {
        this.collisionDetector = collisionDetector;
    }


}
