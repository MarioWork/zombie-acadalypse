package org.academiadecodigo.altcatras65.game_objects.props.perks;

import org.academiadecodigo.altcatras65.util.GameSprites;
import org.academiadecodigo.altcatras65.game_objects.props.perks.variants.Fridge;
import org.academiadecodigo.altcatras65.game_objects.props.perks.variants.MasterCoder;
import org.academiadecodigo.altcatras65.grid.Grid;

public class PerkFactory {

    public static Perk createFridge(Grid grid) {
        return new Fridge(PerkType.FRIDGE.getScoreCost(), PerkType.FRIDGE.getRestoreHp(), PerkType.FRIDGE.getRestoreArmor(), grid.makeGridPosition(4, 8, GameSprites.FRIDGE), grid);
    }

    public static Perk createMasterCoder(Grid grid){
        return new MasterCoder(PerkType.MasterCoder.getScoreCost(),PerkType.MasterCoder.getRestoreHp(),PerkType.MasterCoder.getRestoreArmor(), grid.makeGridPosition(10,17
                ,GameSprites.MasterCoder), grid);

    }
}
