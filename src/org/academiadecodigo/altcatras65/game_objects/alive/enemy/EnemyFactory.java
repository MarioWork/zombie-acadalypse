package org.academiadecodigo.altcatras65.game_objects.alive.enemy;


import org.academiadecodigo.altcatras65.util.GameSprites;
import org.academiadecodigo.altcatras65.grid.Grid;

public class EnemyFactory {


    /**
     * Creates a enemy with all the information needed
     *
     * @param grid
     * @return
     */
    public static Enemy createEnemy(int col, int row, Grid grid) {
        GameSprites gameSprite = getRandomZombieSprite();

        return new Enemy(EnemyType.NORMAL.getHp(),
                EnemyType.NORMAL.getArmor(),
                EnemyType.NORMAL.getScoreValue(),
                EnemyType.NORMAL.getDamage(),
                grid.makeGridPosition(col, row, gameSprite), grid, EnemyType.NORMAL);
    }

    private static GameSprites getRandomZombieSprite() {
        int rand = ((int) Math.random() * 2);
        return rand == 1 ? GameSprites.NORMALENEMY : GameSprites.BLUEENEMY;
    }
}
