package org.academiadecodigo.altcatras65.grid.gridposition;


import org.academiadecodigo.altcatras65.util.GameSprites;

public interface GridPosition {


    /**
     * Gets the width of the position in the grid
     * @return
     */
    public int getWidth();


    /**
     * Gets the height of the position in the grid
     * @return
     */
    public int getHeight();


    /**
     * Gets the position column in the grid
     * @return
     */
    public int getCol();

    /**
     * Gets the position row in the grid
     * @return
     */
    public int getRow();

    /**
     * Displays the position on the grid
     */
    public void show();

    /**
     * Hides the position on the grid
     */
    public void hide();


    /**
     * Compares the equality of the parameter position with all other gameobjects
     *
     * @param pos
     * @return
     */
    public boolean equals(GridPosition pos);

    /**
     * Compares the current position parameter with the possible next position
     *
     * @param direction
     * @param pos
     * @return
     */
    public boolean compareNextPosition(GridDirectionType direction, GridPosition pos);


    /**
     * Moves the position in a certain direction passed in the parameters
     *
     * @param direction
     * @param distance
     * @param objectType
     */
    public void moveInDirection(GridDirectionType direction, int distance, GameSprites objectType);


}
