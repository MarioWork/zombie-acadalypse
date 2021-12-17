package org.academiadecodigo.altcatras65.util;

public enum GameSprites {
    WALL(""),
    NORMALENEMY("resources/images/zombies/zombie"),
    BLUEENEMY("resources/images/zombies/bluezombie"),
    PLAYER("resources/images/player/player"),
    BALL("resources/images/props/ball/ball"),
    FRIDGE("resources/images/props/fridge.png"),
    MasterCoder("resources/images/props/mari.png"),
    HEART("resources/images/heart.png"),
    GAMEOVER("resources/images/menus/game-over.png"),
    NEXTROUND("resources/images/menus/next-round.png"),
    GAME("resources/images/game/map.jpg"),
    NOT_ENOUGH_SCORE("resources/images/menus/not-enough-score.png"),
    BEER_MENU("resources/images/menus/beer.png"),
    INSTRUCTIONS("resources/images/menus/instructions.jpg"),
    MAIMENU("resources/images/menus/main-menu.jpg"),
    UKELELE("resources/images/props/ukelele.png"),
    NOT_ENOUGH_SCORE_MASTER_CODER("resources/images/menus/not-enough-score-master-coder.png"),
    MASTER_CODER_UKELELE("resources/images/menus/master-coder-ukelele.png");


    private String getSpritePath;


    GameSprites(String getSpritePath) {
        this.getSpritePath = getSpritePath;
    }

    public String getGetSpritePath() {
        return getSpritePath;
    }
}
