package org.academiadecodigo.altcatras65.simple_grafics;

import org.academiadecodigo.altcatras65.util.GameSprites;
import org.academiadecodigo.altcatras65.grid.gridposition.AbstractGridPosition;
import org.academiadecodigo.altcatras65.grid.gridposition.GridDirectionType;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SGGridPosition extends AbstractGridPosition {

    private Picture image;
    private SGGrid sGGrid;
    private Rectangle rectangle;

    /**
     * Create a new position in a specific col and row of the grid
     *
     * @param col
     * @param row
     * @param grid
     * @param objectType
     */
    public SGGridPosition(int col, int row, SGGrid grid, GameSprites objectType) {
        super(col, row, grid);
        this.sGGrid = grid;
        decideGameSprite(objectType);
        this.rectangle = new Rectangle(sGGrid.columnToX(super.getCol()), this.sGGrid.rowToY(super.getRow()), this.image.getWidth(), this.image.getHeight());
        this.show();
    }

    /**
     * Create a new position in a random col and row
     *
     * @param grid
     * @param objectType
     */
    public SGGridPosition(SGGrid grid, GameSprites objectType) {
        super((int) (Math.random() * grid.getCols()), (int) (Math.random() * grid.getRows()), grid);
        this.sGGrid = grid;
        decideGameSprite(objectType);
        this.rectangle = new Rectangle(sGGrid.columnToX(super.getCol()), this.sGGrid.rowToY(super.getRow()), this.image.getWidth(), this.image.getHeight());
        this.show();
    }

    /**
     * Create a new position in specific col && col && width && row
     *
     * @param width
     * @param height
     * @param col
     * @param row
     * @param grid
     * @param objectType
     */
    public SGGridPosition(int width, int height, int col, int row, SGGrid grid, GameSprites objectType) {
        super(col, row, grid);
        sGGrid = grid;

        decideGameSprite(objectType);
        this.rectangle = new Rectangle(sGGrid.columnToX(super.getCol()), this.sGGrid.rowToY(super.getRow()), width, height);
        this.rectangle.setColor(new Color(93, 90, 87));
        this.show();
    }


    public void decideGameSprite(GameSprites gameSprite) {
        switch (gameSprite) {
            case PLAYER:
                createPlayer(gameSprite);
                break;
            case BALL:
                createThrowable(gameSprite);
                break;
            case BLUEENEMY:
            case NORMALENEMY:
                createEnemy(gameSprite);
                break;
            case FRIDGE:
                createFridge(gameSprite);
                break;
            case MasterCoder:
                createMasterCoder(gameSprite);
                break;
            default:
                createWall();
                break;
        }
    }

    private void createMasterCoder(GameSprites gameSprite) {
        this.image = new Picture(this.sGGrid.columnToX(super.getCol()), this.sGGrid.rowToY(super.getRow()), gameSprite.getGetSpritePath());
    }

    private void createFridge(GameSprites gameSprites) {
        this.image = new Picture(this.sGGrid.columnToX(super.getCol()), this.sGGrid.rowToY(super.getRow()), gameSprites.getGetSpritePath());
    }

    private void createEnemy(GameSprites gameSprites) {
        this.image = new Picture(this.sGGrid.columnToX(super.getCol()), this.sGGrid.rowToY(super.getRow()), gameSprites.getGetSpritePath() + "_up.png");

    }

    private void createPlayer(GameSprites gameSprites) {
        this.image = new Picture(this.sGGrid.columnToX(super.getCol()), this.sGGrid.rowToY(super.getRow()), gameSprites.getGetSpritePath() + "_up.png");
    }

    private void createThrowable(GameSprites gameSprites) {
        //this.image = new Picture(this.sGGrid.columnToX(super.getCol()), this.sGGrid.rowToY(super.getRow()), objectType.getGetSpritePath() + "_up.png";
    }

    private void createWall() {
        this.rectangle = new Rectangle(sGGrid.columnToX(20), sGGrid.rowToY(10), sGGrid.getCellSize(), 1);
    }


    /**
     * Gets the width of the position in the grid
     *
     * @return
     */
    @Override
    public int getWidth() {
        if (image != null) {
            return this.image.getWidth();
        }

        return this.rectangle.getWidth();
    }

    /**
     * Gets the height of the position in the grid
     *
     * @return
     */
    @Override
    public int getHeight() {

        if (image != null) {
            return this.image.getHeight();
        }

        return this.rectangle.getHeight();
    }


    /**
     * Displays the position on the grid
     */
    @Override
    public void show() { //Displays the position in the grid
        if (image != null) {
            this.image.draw();
            //this.rectangle.draw();
        } /*else {
            this.rectangle.draw();
        }*/
    }

    /**
     * Hides the position on the grid
     */
    @Override
    public void hide() { //Hides the position in the grid
        if (image != null) {
            this.image.delete();
            this.rectangle.delete();
        } else {
            this.rectangle.delete();
        }
    }


    /**
     * Moves the position in a certain direction passed in the parameters
     *
     * @param direction
     * @param distance
     * @param gameSprites
     */
    @Override
    public void moveInDirection(GridDirectionType direction, int distance, GameSprites gameSprites) {
        switch (direction) {
            case UP:
                if (sGGrid.rowToY(super.getRow()) - distance * sGGrid.getCellSize() > sGGrid.PADDING) {
                    this.image.load(gameSprites.getGetSpritePath() + "_up.png");
                    this.image.translate(0, -distance * sGGrid.getCellSize());
                    this.rectangle.translate(0, -distance * sGGrid.getCellSize());
                    this.moveUp(distance);
                }
                break;
            case DOWN:
                if (sGGrid.rowToY(super.getRow()) + distance * sGGrid.getCellSize() < sGGrid.getHeight()) {
                    this.image.load(gameSprites.getGetSpritePath() + "_down.png");
                    this.image.translate(0, distance * sGGrid.getCellSize());
                    this.rectangle.translate(0, distance * sGGrid.getCellSize());
                    this.moveDown(distance);
                }
                break;
            case LEFT:
                if (sGGrid.columnToX(super.getCol()) - distance * sGGrid.getCellSize() > sGGrid.PADDING) {
                    this.image.load(gameSprites.getGetSpritePath() + "_left.png");
                    this.image.translate(-distance * sGGrid.getCellSize(), 0);
                    this.rectangle.translate(-distance * sGGrid.getCellSize(), 0);
                    this.moveLeft(distance);
                }
                break;
            case RIGHT:
                if (sGGrid.columnToX(super.getCol()) + distance * sGGrid.getCellSize() < sGGrid.getWidth()) {
                    this.image.load(gameSprites.getGetSpritePath() + "_right.png");
                    this.image.translate(distance * sGGrid.getCellSize(), 0);
                    this.rectangle.translate(distance * sGGrid.getCellSize(), 0);
                    this.moveRight(distance);
                }
                break;
        }
    }
}
