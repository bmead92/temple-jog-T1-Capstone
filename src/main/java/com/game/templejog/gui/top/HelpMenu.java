package com.game.templejog.gui.top;

import javax.swing.*;

import com.game.templejog.Game;

public class HelpMenu {
    /**
     * setUpHelpGUI is called when the help button is pressed, creating a JFrame with helpful information about the game
     */
    public static void setUpHelpGUI(Game game) {
        JFrame helpFrame = new JFrame("Help Menu");
        helpFrame.setSize(250, 250);
        helpFrame.setLocationRelativeTo(null);
        JTextArea helpMessage = new JTextArea(game.getGameText().get("gameHelp"));
        helpMessage.setEditable(false);
        JOptionPane.showMessageDialog(helpFrame, helpMessage, "Help Menu", JOptionPane.WARNING_MESSAGE);
    }
}

