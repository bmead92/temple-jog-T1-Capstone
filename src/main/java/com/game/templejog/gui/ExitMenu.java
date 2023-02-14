package com.game.templejog.gui;

import javax.swing.*;
import java.awt.*;

public class ExitMenu {
    public static void setUpExitGUI(GUI gui) {
        final JFrame exitFrame = new JFrame("Exit Menu");
        exitFrame.setLayout(new BorderLayout());
        final JTextArea exitPrompt = new JTextArea("Are you sure you want to quit?");
        final JButton exitYes = new JButton("Yes");
        final JButton exitNo = new JButton("No");
        exitYes.addActionListener(e -> {
            if (e.getSource() == exitYes) {
                exitFrame.dispose();
                gui.getMainContainer().dispose();
            }
            //TODO: close exit menu window when no button is pressed
            if (e.getSource() == exitNo) {
                exitFrame.dispose();
            }
        });
        exitFrame.setBounds(0, 0, GUI.GAME_WIDTH / 4, GUI.GAME_HEIGHT / 4);
        exitFrame.add(exitPrompt, BorderLayout.PAGE_START);
        exitFrame.add(exitYes, BorderLayout.LINE_START);
        exitFrame.add(exitNo, BorderLayout.LINE_END);
        exitFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        exitFrame.setVisible(true);
    }
}
