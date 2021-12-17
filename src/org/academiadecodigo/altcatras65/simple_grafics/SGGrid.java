package org.academiadecodigo.altcatras65.simple_grafics;

import org.academiadecodigo.altcatras65.util.GameSprites;
import org.academiadecodigo.altcatras65.grid.Grid;
import org.academiadecodigo.altcatras65.grid.gridposition.GridPosition;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class SGGrid implements Grid {

    public static final int PADDING = 10;
    public final int cellSize = 50;

    private Picture image;
    private int cols;
    private int rows;

    public SGGrid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        image = new Picture(PADDING, PADDING, GameSprites.GAME.getGetSpritePath());
    }

    /**
     * Initializes the grid
     */
    @Override
    public void init() {
        image.draw();
    }

    /**
     * Returns the number of cols of the grid
     *
     * @return
     */
    @Override
    public int getCols() {
        return cols;
    }

    /**
     * Returns the numbers of rows of the grid
     *
     * @return
     */
    @Override
    public int getRows() {
        return rows;
    }


    /**
     * Creates a grid position in a random position
     *
     * @param objectType
     * @return GridPosition
     */
    @Override
    public GridPosition makeGridPosition(GameSprites objectType) {
        return new SGGridPosition(this, objectType);
    }


    /**
     * Creates a grid position with a specific col and row
     *
     * @param col
     * @param row
     * @param objectType
     * @return
     */
    @Override
    public GridPosition makeGridPosition(int col, int row, GameSprites objectType) {
        return new SGGridPosition(col, row, this, objectType);
    }

    /**
     * Create a new position in specific col && col && width && row
     *
     * @param width
     * @param height
     * @param col
     * @param row
     * @param objectType
     */
    @Override
    public GridPosition makeGridPosition(int width, int height, int col, int row, GameSprites objectType) {
        return new SGGridPosition(width, height, col, row, this, objectType);
    }

    /**
     * Returns the width of the grid
     *
     * @return
     */
    public int getWidth() {
        return image.getWidth();
    }

    /**
     * Returns the height of the grid
     *
     * @return
     */
    public int getHeight() {
        return image.getHeight();
    }

    /**
     * Returns the x of the grid
     *
     * @return
     */
    public int getX() {
        return image.getX();
    }

    /**
     * Returns the y of the grid
     *
     * @return
     */
    public int getY() {
        return image.getY();
    }

    /**
     * Returns the cell size of the grid
     *
     * @return
     */
    public int getCellSize() {
        return cellSize;
    }

    /**
     * Converts a row to a number of pixel
     *
     * @param row
     * @return
     */
    public int rowToY(int row) {
        return cellSize * row + PADDING;
    }

    /**
     * Converts a column to a number of pixel
     *
     * @param column
     * @return
     */
    public int columnToX(int column) {
        return cellSize * column + PADDING;
    }
}
