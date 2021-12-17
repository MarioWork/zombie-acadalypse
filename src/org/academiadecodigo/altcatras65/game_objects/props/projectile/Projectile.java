package org.academiadecodigo.altcatras65.game_objects.props.projectile;

import org.academiadecodigo.altcatras65.game_objects.props.Prop;
import org.academiadecodigo.altcatras65.grid.Grid;
import org.academiadecodigo.altcatras65.grid.gridposition.GridDirectionType;
import org.academiadecodigo.altcatras65.grid.gridposition.GridPosition;

public abstract class Projectile extends Prop implements Throwable {
    private int travelDistance;
    private int damage;
    private ProjectileType projectileType;
    private int lifeSpan;
    private GridDirectionType directionType;


    public Projectile(int travelDistance, int damage, GridPosition position, ProjectileType projectileType, Grid grid) {
        super(position, grid);
        this.travelDistance = travelDistance;
        this.damage = damage;
        this.projectileType = projectileType;
        this.lifeSpan = 3000;
    }

    public ProjectileType getWeaponType() {
        return projectileType;
    }

    public int getDamage() {
        return damage;
    }


    public int getlifeSpan() {
        return lifeSpan;
    }

    public GridDirectionType getDirectionType() {
        return directionType;
    }

    public void setDirectionType(GridDirectionType directionType) {
        this.directionType = directionType;
    }

    public void setLifeSpan(int lifeSpan) {
        this.lifeSpan = lifeSpan;
    }
}
