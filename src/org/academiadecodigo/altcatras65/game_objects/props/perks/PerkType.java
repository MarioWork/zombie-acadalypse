package org.academiadecodigo.altcatras65.game_objects.props.perks;

public enum PerkType {
    FRIDGE(100, 0, 250),
    MasterCoder(0, 100, 500);

    private int restoreHp;
    private int restoreArmor;
    private int scoreCost;

    PerkType(int restoreHp, int restoreArmor, int scoreCost) {
        this.restoreHp = restoreHp;
        this.restoreArmor = restoreArmor;
        this.scoreCost = scoreCost;
    }

    public int getRestoreHp() {
        return restoreHp;
    }

    public int getRestoreArmor() {
        return restoreArmor;
    }

    public int getScoreCost() {
        return scoreCost;
    }
}
