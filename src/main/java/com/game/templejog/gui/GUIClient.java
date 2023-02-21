package com.game.templejog.gui;

import com.game.templejog.Game;
import com.game.templejog.Temple;
import com.game.templejog.client.FileLoader;

import java.io.IOException;

public class GUIClient {

    public static void main(String[] args) throws IOException {
        Temple gameFiles = FileLoader.jsonLoader("JSON/gameFiles.json");
        Game game = new Game(gameFiles);
//        game.setPlaySound(true);
        RunGUI runGui = new RunGUI(game);
        StartMenu sm = new StartMenu(runGui);
        sm.gameStartScreen(game);
    }
}
