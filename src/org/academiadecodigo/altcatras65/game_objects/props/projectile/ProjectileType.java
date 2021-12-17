package org.academiadecodigo.altcatras65.game_objects.props.projectile;

public enum ProjectileType {
    MOUSE(100, 2);


    private int damage;
    private int travelDistance;

    ProjectileType(int damage, int travelDistance) {
        this.damage = damage;
        this.travelDistance = travelDistance;
    }

    public int getDamage() {
        return damage;
    }

    public int getTravelDistance() {
        return travelDistance;
    }


}
