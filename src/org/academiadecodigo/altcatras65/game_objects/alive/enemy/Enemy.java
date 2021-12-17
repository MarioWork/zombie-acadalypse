package org.academiadecodigo.altcatras65.game_objects.alive.enemy;

import org.academiadecodigo.altcatras65.util.GameSprites;
import org.academiadecodigo.altcatras65.game_objects.alive.Alive;
import org.academiadecodigo.altcatras65.grid.Grid;
import org.academiadecodigo.altcatras65.grid.gridposition.AbstractGridPosition;
import org.academiadecodigo.altcatras65.grid.gridposition.GridDirectionType;
import org.academiadecodigo.altcatras65.grid.gridposition.GridPosition;


public class Enemy extends Alive {
    private int scoreValue;
    private int damage;
    private EnemyType type;
    private GridDirectionType currentDirection;
    private int directionChangeLevel = 8; // randomness of direction changes from 1 to 10


    public Enemy(int hp, int armor, int scoreValue, int damage, GridPosition position, Grid grid, EnemyType type) {
        super(hp, armor, position, grid);
        this.scoreValue = scoreValue;
        this.type = type;
        currentDirection = GridDirectionType.getRandom();
    }

    public EnemyType getType() {
        return type;
    }

    /**
     * Gets the score value of the enemy
     *
     * @return
     */
    public int getScoreValue() {
        return scoreValue;
    }

    /**
     * Implements the damage taking logic for the enemy
     *
     * @param dmg
     */
    @Override
    public void hit(int dmg) {
        super.setHp(super.getHp() - dmg);

        if (super.getHp() <= 0) {
            setAlive(false);
            ((AbstractGridPosition) getPos()).setPos(-1, -1);
            getPos().hide();
        }
        System.out.println("Enemy hp: " + getHp());
    }

    /**
     * Gets a new random direction to move
     *
     * @return
     */
    public GridDirectionType chooseDirection() {

        //Move in the same direction by default
        GridDirectionType newDirection = currentDirection;

        // Sometimes, change direction
        if (Math.random() > ((double) directionChangeLevel) / 10) {
            newDirection = GridDirectionType.getRandom();

            // but we do not want to perform U turns
            if (newDirection.isOpposite(currentDirection)) {
                return chooseDirection();
            }
        }

        return newDirection;
    }


    /**
     * Moves the enemy in the direction in the parameters
     *
     * @param direction
     */
    @Override
    public void move(GridDirectionType direction) {
        if (!isAlive()) {
            return;
        }
        int maxTries = 200;

        GridDirectionType newDirection = direction;
        //if is not safe, so if the next pos is a wall, choose a random direction
        while (!getCollisionDetector().isSafe(newDirection, this)) {
            newDirection = GridDirectionType.getRandom();

            //if is stuck between walls ands other enemies, try again lather, and decrease her maxtries
            if (maxTries == 0) {
                return;
            }
            maxTries--;
        }

        this.currentDirection = newDirection;
        for (int i = 0; i < type.getSpeed(); i++) {
            getPos().moveInDirection(newDirection, 1, GameSprites.NORMALENEMY);
        }
    }
}
