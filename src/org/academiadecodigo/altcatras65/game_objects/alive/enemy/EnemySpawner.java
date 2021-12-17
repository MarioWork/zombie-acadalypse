package org.academiadecodigo.altcatras65.game_objects.alive.enemy;

import org.academiadecodigo.altcatras65.grid.Grid;

public class EnemySpawner {

    private enum SpawnSpot {
        OUTSIDE1(19, 4),
        OUTSIDE2(22, 4),
        OUTSIDE3(18, 2),
        OUTSIDE4(22, 2),
        INSIDE1(17, 17),
        INSIDE2(16, 15),
        INSIDE3(17, 16),
        INSIDE4(16, 14);

        private int col;
        private int row;

        SpawnSpot(int col, int row) {
            this.col = col;
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }

        public static SpawnSpot getRandom() {
            return SpawnSpot.values()[(int) (Math.random() * SpawnSpot.values().length)];
        }
    }

    public static Enemy spawnEnemy(Grid grid) {
        SpawnSpot spot = SpawnSpot.getRandom();
        return EnemyFactory.createEnemy(spot.getCol(), spot.getRow(), grid);
    }
}
