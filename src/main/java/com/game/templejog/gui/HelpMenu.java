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
        JOptionPane.showMessageDialog(helpFrame, helpMessage, "Help Menu", JOptionPane.WARNING_MESSAGE);
    }
}

