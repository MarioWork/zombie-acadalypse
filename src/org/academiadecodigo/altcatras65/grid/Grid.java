package org.academiadecodigo.altcatras65.grid;

import org.academiadecodigo.altcatras65.util.GameSprites;
import org.academiadecodigo.altcatras65.grid.gridposition.GridPosition;

public interface Grid {


    /**
     * Initializes the grid
     */
    public void init();


    /**
     * Returns the number of cols of the grid
     *
     * @return
     */
    public int getCols();

    /**
     * Returns the numbers of rows of the grid
     *
     * @return
     */
    public int getRows();

    /**
     * Returns the cell size of the grid
     *
     * @return
     */
    int getCellSize();

    /**
     * Creates a grid position in a random position
     *
     * @param objectType
     * @return GridPosition
     */
    public GridPosition makeGridPosition(GameSprites objectType);


    /**
     * Creates a grid position with a specific col and row
     *
     * @param col
     * @param row
     * @param objectType
     * @return
     */
    public GridPosition makeGridPosition(int col, int row, GameSprites objectType);

    /**
     * Create a new position in specific col && col && width && row
     *
     * @param width
     * @param height
     * @param col
     * @param row
     * @param objectType
     */
    public GridPosition makeGridPosition(int width, int height, int col, int row, GameSprites objectType);

}

