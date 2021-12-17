package org.academiadecodigo.altcatras65;


import org.academiadecodigo.altcatras65.menu.MainMenu;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, UnsupportedAudioFileException {

        MainMenu mainMenu = new MainMenu();
        mainMenu.init();
    }
}
