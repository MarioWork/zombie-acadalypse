package org.academiadecodigo.altcatras65.grid.gridposition;

import org.academiadecodigo.altcatras65.grid.Grid;

public enum GridDirectionType {

    UP,
    DOWN,
    LEFT,
    RIGHT;

    //Detects if two directions are opposite | @param dir the direction to compare with && @return true if directions are opposite

    public boolean isOpposite(GridDirectionType dir) {
        return dir.equals(oppositeDirection());
    }


    /**
     * Obtains the oppositeDirection of the current instance
     * @return
     */
    public GridDirectionType oppositeDirection() {

        GridDirectionType opposite = null;
        switch (this) {
            case UP:
                opposite = GridDirectionType.getRandom();
                if (opposite.equals(UP)) {
                    opposite = GridDirectionType.getRandom();
                }
                else {
                    opposite = GridDirectionType.DOWN;
                }
                break;
            case DOWN:
                opposite = GridDirectionType.UP;
                break;
            case LEFT:
                opposite = GridDirectionType.RIGHT;
                break;
            case RIGHT:
                opposite = GridDirectionType.LEFT;
                break;
        }
        return opposite;
    }

    /**
     * Gets a random direction from the current enum values
     * @return
     */
    public static GridDirectionType getRandom() {
        int rand = (int) (Math.random() * GridDirectionType.values().length);
        return GridDirectionType.values()[rand];
    }
    /*public GridDirectionType getOther3Position(GridDirectionType currentDirection) {
        int random = (int)(Math.random()*4);

        for (int i = 0; i < GridDirectionType.values().length; i++) {
            if (currentDirection == GridDirectionType.values()[i] ) {
                currentDirection = GridDirectionType.values()[random];

            }
            currentDirection = GridDirectionType.values()[random];
        }
        return currentDirection;
    }*/

}
