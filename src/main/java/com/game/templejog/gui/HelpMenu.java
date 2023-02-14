package com.game.templejog.gui;

import javax.swing.*;

import com.game.templejog.Game;

public class HelpMenu {
    /**
     * setUpHelpGUI is called when the help button is pressed, creating a JFrame with helpful information about the game
     */
    public static void setUpHelpGUI(Game game) {
        JFrame helpFrame = new JFrame("Help Menu");
        JTextArea helpMessage = new JTextArea(game.getGameText().get("gameHelp"));
        helpFrame.add(helpMessage);
        helpFrame.setBounds(0, 0, GUI.GAME_WIDTH / 2, GUI.GAME_HEIGHT / 2);
        helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        helpFrame.setVisible(true);
    }
}
