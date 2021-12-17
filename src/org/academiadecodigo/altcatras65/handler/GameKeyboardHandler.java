package org.academiadecodigo.altcatras65.handler;

import org.academiadecodigo.altcatras65.game.Game;
import org.academiadecodigo.altcatras65.menu.MainMenu;
import org.academiadecodigo.altcatras65.game_objects.alive.player.Player;
import org.academiadecodigo.altcatras65.grid.gridposition.GridDirectionType;
import org.academiadecodigo.altcatras65.util.Sound;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class GameKeyboardHandler implements KeyboardHandler {

    private Player player;
    private Keyboard keyboard;
    private Game game;
    private MainMenu mainMenu;


    public GameKeyboardHandler(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        this.game = this.mainMenu.getGame();
        this.player = game.getPlayer();

        //adds the keys needed to the keyboard
        this.keyboard = new Keyboard(this);
        addKey(KeyboardEvent.KEY_UP);
        addKey(KeyboardEvent.KEY_DOWN);
        addKey(KeyboardEvent.KEY_LEFT);
        addKey(KeyboardEvent.KEY_RIGHT);
        addKey(KeyboardEvent.KEY_W);
        addKey(KeyboardEvent.KEY_A);
        addKey(KeyboardEvent.KEY_S);
        addKey(KeyboardEvent.KEY_D);
        addKey(KeyboardEvent.KEY_I);
        addKey(KeyboardEvent.KEY_SPACE);
    }

    /**
     * Checks the key pressed events
     * and executes the right action for the key pressed
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyboardEvent e) {
        if (!mainMenu.isGameStarted() && (!(e.getKey() == KeyboardEvent.KEY_SPACE || e.getKey() == KeyboardEvent.KEY_I))) {
            return;
        }

        switch (e.getKey()) {
            case KeyboardEvent.KEY_W:
            case KeyboardEvent.KEY_UP:
                this.player.move(GridDirectionType.UP);
                break;
            case KeyboardEvent.KEY_S:
            case KeyboardEvent.KEY_DOWN:
                this.player.move(GridDirectionType.DOWN);
                break;
            case KeyboardEvent.KEY_A:
            case KeyboardEvent.KEY_LEFT:
                this.player.move(GridDirectionType.LEFT);
                break;
            case KeyboardEvent.KEY_D:
            case KeyboardEvent.KEY_RIGHT:
                this.player.move(GridDirectionType.RIGHT);
                break;
            case KeyboardEvent.KEY_I:
                if (!mainMenu.isGameStarted()) {
                    if (mainMenu.isShowingInstructions()) {
                        mainMenu.setShowingInstructions(false);
                        mainMenu.getInstructions().delete();
                        break;
                    } else {
                        mainMenu.setShowingInstructions(true);
                        mainMenu.getInstructions().draw();
                        break;
                    }
                } else {
                    this.game.checkConsume();
                }
                break;
            case KeyboardEvent.KEY_SPACE:
                if (!mainMenu.isGameStarted()) {
                    mainMenu.setGameStarted(true);
                } else {
                    //Audio
                    Sound shootSound = new Sound("/resources/audio/projectile-throw-sound.wav");
                    shootSound.play(true);
                    this.player.shoot();
                }
                break;
        }
    }

    /**
     * Checks the key released events
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyboardEvent e) {

    }

    /**
     * Adds a key to an event and adds the event to the property keyboard
     *
     * @param key
     */
    public void addKey(int key) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        event.setKey(key);
        this.keyboard.addEventListener(event);

        event = new KeyboardEvent();
        event.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        event.setKey(key);
        this.keyboard.addEventListener(event);
    }
}
