package org.academiadecodigo.altcatras65.game_objects.alive.enemy;

public enum EnemyType {
    NORMAL(100,0,50,20,1);


    private int hp;
    private int armor;
    private int scoreValue;
    private int damage;
    private int speed;

    EnemyType(int hp, int armor, int scoreValue, int damage, int speed) {
        this.hp = hp;
        this.armor = armor;
        this.scoreValue = scoreValue;
        this.damage = damage;
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public int getArmor() {
        return armor;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }
}
