package org.academiadecodigo.altcatras65.grid.gridposition;

import org.academiadecodigo.altcatras65.game_objects.alive.player.Player;
import org.academiadecodigo.altcatras65.grid.Grid;

public abstract class AbstractGridPosition implements GridPosition {

    private int col;
    private int row;
    private Grid grid;

    public AbstractGridPosition(int col, int row, Grid grid) {
        this.col = col;
        this.row = row;
        this.grid = grid;
    }

    /**
     * Returns the grid of the current position
     *
     * @return
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Gets the position column in the grid
     *
     * @return
     */
    @Override
    public int getCol() {
        return col;
    }

    /**
     * Gets the position row in the grid
     *
     * @return
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Sets the new col and row of the grid position
     *
     * @param col
     * @param row
     */
    public void setPos(int col, int row) {
        this.col = col;
        this.row = row;
    }


    /**
     * Compares the equality of the parameter position with all other gameobjects
     *
     * @param pos
     * @return
     */
    @Override
    public boolean equals(GridPosition pos) {
        return !(getCol() >= pos.getCol() + pos.getWidth() / grid.getCellSize() ||
                getRow() >= pos.getRow() + pos.getHeight() / grid.getCellSize() ||
                getCol() + getWidth() / grid.getCellSize() <= pos.getCol() ||
                getRow() + getHeight() / grid.getCellSize() <= pos.getRow());
    }


    /**
     * Compares the current position parameter with the possible next position
     *
     * @param direction
     * @param pos
     * @return
     */
    public boolean compareNextPosition(GridDirectionType direction, GridPosition pos) {
        switch (direction) {
            case UP:
                return !(getCol() >= pos.getCol() + pos.getWidth() / grid.getCellSize() ||
                        getRow() - 1 >= pos.getRow() + pos.getHeight() / grid.getCellSize() ||
                        getCol() + getWidth() / grid.getCellSize() <= pos.getCol() ||
                        getRow() - 1 + getHeight() / grid.getCellSize() <= pos.getRow());
            case LEFT:
                return !(getCol() - 1 >= pos.getCol() + pos.getWidth() / grid.getCellSize() ||
                        getRow() >= pos.getRow() + pos.getHeight() / grid.getCellSize() ||
                        getCol() - 1 + getWidth() / grid.getCellSize() <= pos.getCol() ||
                        getRow() + getHeight() / grid.getCellSize() <= pos.getRow());
            case RIGHT:
                return !(getCol() + 1 >= pos.getCol() + pos.getWidth() / grid.getCellSize() ||
                        getRow() >= pos.getRow() + pos.getHeight() / grid.getCellSize() ||
                        getCol() + 1 + getWidth() / grid.getCellSize() <= pos.getCol() ||
                        getRow() + getHeight() / grid.getCellSize() <= pos.getRow());
            default:
                return !(getCol() >= pos.getCol() + pos.getWidth() / grid.getCellSize() ||
                        getRow() + 1 >= pos.getRow() + pos.getHeight() / grid.getCellSize() ||
                        getCol() + getWidth() / grid.getCellSize() <= pos.getCol() ||
                        getRow() + 1 + getHeight() / grid.getCellSize() <= pos.getRow());
        }
    }


    /**
     * Moves the current position up logically
     *
     * @param dist
     */
    public void moveUp(int dist) {
        int maxRowsUp = dist < getRow() ? dist : getRow();
        setPos(getCol(), getRow() - maxRowsUp);
    }

    /**
     * Moves the current position down logically
     *
     * @param dist
     */
    public void moveDown(int dist) {
        int maxRowsDown = dist > getGrid().getRows() - (getRow() + 1) ? getGrid().getRows() - (getRow() + 1) : dist;
        setPos(getCol(), getRow() + maxRowsDown);
    }

    /**
     * Moves the current position left logically
     *
     * @param dist
     */
    public void moveLeft(int dist) {
        int maxRowsLeft = dist < getCol() ? dist : getCol();
        setPos(getCol() - maxRowsLeft, getRow());

    }

    /**
     * Moves the current position right logically
     *
     * @param dist
     */
    public void moveRight(int dist) {
        int maxRowsRight = dist > getGrid().getCols() - (getCol() + 1) ? getGrid().getCols() - (getCol() + 1) : dist;
        setPos(getCol() + maxRowsRight, getRow());

    }


}
