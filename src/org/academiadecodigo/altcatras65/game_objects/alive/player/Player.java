package org.academiadecodigo.altcatras65.game_objects.alive.player;

import org.academiadecodigo.altcatras65.game.Game;
import org.academiadecodigo.altcatras65.util.GameSprites;
import org.academiadecodigo.altcatras65.game_objects.alive.Alive;
import org.academiadecodigo.altcatras65.game_objects.props.projectile.Projectile;
import org.academiadecodigo.altcatras65.game_objects.props.projectile.ProjectileFactory;
import org.academiadecodigo.altcatras65.game_objects.props.projectile.ProjectileType;
import org.academiadecodigo.altcatras65.grid.Grid;
import org.academiadecodigo.altcatras65.grid.gridposition.GridDirectionType;
import org.academiadecodigo.altcatras65.grid.gridposition.GridPosition;

public class Player extends Alive {
    public static final int MAX_HP = 100;
    public static final int MAX_ARMOR = 100;
    public static final int SPEED = 1;
    public final int SHOOT_COOLDOWN = 5;
    private ProjectileType weapon;
    private GridDirectionType currentDirection;
    private int score;
    private int shootCooldown;

    public Player(int hp, int armor, ProjectileType weapon, GridPosition position, Grid grid) {
        super(hp, armor, position, grid);
        this.weapon = weapon;
        this.currentDirection = GridDirectionType.UP;
        this.shootCooldown = SHOOT_COOLDOWN;
    }

    public int getShootCooldown() {
        return shootCooldown;
    }

    public void setShootCooldown(int shootCooldown) {
        this.shootCooldown = shootCooldown;
    }

    @Override
    public void hit(int dmg) {
        if (super.getArmor() <= 0) {
            super.setHp(super.getHp() - dmg);
        } else {
            super.setArmor(super.getArmor() - dmg);
        }
        if (super.getHp() == 0) {
            setAlive(false);
            super.getPos().hide();
            System.out.println("game over");
            score = 0;
            return;
        }

        System.out.println("Player HP: " + getHp());
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void move(GridDirectionType direction) {
        this.currentDirection = direction;

        GridPosition pos = getPos();
        if (getCollisionDetector().isSafe(direction, this)) {
            pos.moveInDirection(direction, SPEED, GameSprites.PLAYER);
        } else {
            getCollisionDetector().check(direction, this);
        }
    }

    public ProjectileType getWeapon() {
        return weapon;
    }

    public GridDirectionType getCurrentDirection() {
        return currentDirection;
    }

    public void shoot() {
        if (shootCooldown > 0) {
            return;
        }
        Projectile projectile = ProjectileFactory.createWeapon(super.getPos().getCol(), super.getPos().getRow(), super.getGrid());
        projectile.setCollisionDetector(getCollisionDetector());
        projectile.setDirectionType(currentDirection);
        Game.addProjectile(projectile);
        this.shootCooldown = SHOOT_COOLDOWN;
    }
}
