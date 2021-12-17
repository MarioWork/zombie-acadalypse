package org.academiadecodigo.altcatras65.menu;

import org.academiadecodigo.altcatras65.game.Game;
import org.academiadecodigo.altcatras65.util.GameSprites;
import org.academiadecodigo.altcatras65.handler.GameKeyboardHandler;
import org.academiadecodigo.altcatras65.simple_grafics.SGGrid;
import org.academiadecodigo.altcatras65.util.Sound;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class MainMenu {

    private boolean gameStarted;
    private Game game;
    private Picture mainMenu;
    private Picture instructions;

    private boolean showingInstructions;

    public MainMenu() throws IOException, UnsupportedAudioFileException {
        gameStarted = false;
        showingInstructions = false;
        game = new Game(28, 20, 350);
    }

    public void init() {
        mainMenu = new Picture(SGGrid.PADDING, SGGrid.PADDING, GameSprites.MAIMENU.getGetSpritePath());
        Sound soundTrack = new Sound("/resources/audio/soundtrack.wav");
        soundTrack.setLoop(1000);
        instructions = new Picture(SGGrid.PADDING, SGGrid.PADDING, GameSprites.INSTRUCTIONS.getGetSpritePath());
        new GameKeyboardHandler(this);
        starGame();
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public Picture getInstructions() {
        return instructions;
    }

    public boolean isShowingInstructions() {
        return showingInstructions;
    }

    public void setShowingInstructions(boolean showingInstructions) {
        this.showingInstructions = showingInstructions;
    }

    public void starGame() {
        game.init();
        mainMenu.draw();
        while (!gameStarted) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mainMenu.delete();
        game.start();

    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public Game getGame() {
        return game;
    }
}
