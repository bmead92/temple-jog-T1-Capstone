package com.game.templejog.gui;

import com.game.templejog.Game;

import javax.swing.*;
import java.awt.*;

public class WinAndLossScreen {
    private Game game;
    private JFrame winAndLossFrame;
    private JTextArea winAndLossTextArea;
    private JButton confirmationButton;

    public WinAndLossScreen(Game game, JFrame mainContainer) {
        this.game = game;
        this.winAndLossFrame = new JFrame();
        this.winAndLossTextArea = new JTextArea();
        this.confirmationButton = new JButton();
        this.confirmationButton.addActionListener(e -> {
            if (e.getSource() == this.confirmationButton) {
                // close this menu, close the game, send to title screen.
                this.winAndLossFrame.dispose();
                mainContainer.dispose();
                System.exit(0);
                //TODO: link back to title screen
            }
        });
    }

    public JFrame setUpWinAndLossScreen() {
        final String outOfTime = "You look down as your alarm goes off. It's 18:00....";
        final String outOfLife = game.getGameText().get("outOfLife");
        final String playerDiesMessage = "You collapse until the pressure of everything, unable to continue on...";
        final String successMessage = "You manage to jog back to the landing zone just as the helicopter lands. " +
                "You all take off, eager to get as much distance between yourselves and the temple as possible...";
        final String sortOfWin = game.getGameText().get("sortOfWin");
        final String win = game.getGameText().get("winText");
        boolean playerOutOfHealth = game.getPlayer().getHealth() <= 0;
        boolean playerOutOfSteps = game.getPlayer().getSteps() >= 24;
        if(game.getCommunicatorOff()) {
            if (playerOutOfHealth || playerOutOfSteps) {
                //bombSound();
                this.winAndLossTextArea.setText(String.format("%s\n%s", outOfTime, sortOfWin));
                this.winAndLossFrame.setTitle("YOU (sort of) WIN!");
                this.confirmationButton.setText("Hmm. At least I saved the world.");
            } else {
//                if (game.getPlaySound()) {
//                    Sound.ending("sounds/victory.wav");
//                }
                this.winAndLossTextArea.setText(String.format("%s\n%s", successMessage, win));
                this.winAndLossFrame.setTitle("YOU WIN!");
                this.confirmationButton.setText("Nice.");
            }
        } else {
            this.confirmationButton.setText("Damn, okay.");
            if (playerOutOfHealth) {
                //bombSound();
                this.winAndLossTextArea.setText(String.format("%s\n%s", playerDiesMessage, outOfLife));
                this.winAndLossFrame.setTitle("YOU DIED!");
            } else if (playerOutOfSteps) {
                //bombSound();
                this.winAndLossTextArea.setText(String.format("%s", outOfTime));
                this.winAndLossFrame.setTitle("YOU RAN OUT OF TIME!");
            }
        }
        this.winAndLossTextArea.setLineWrap(true);
        this.winAndLossTextArea.setWrapStyleWord(true);
        this.winAndLossFrame.setLayout(new BorderLayout());
        this.winAndLossFrame.add(this.winAndLossTextArea, BorderLayout.CENTER);
        this.winAndLossFrame.add(this.confirmationButton, BorderLayout.PAGE_END);
        winAndLossFrame.setSize(250, 250);
        winAndLossFrame.setLocationRelativeTo(null);
        this.winAndLossFrame.setVisible(true);
        return this.winAndLossFrame;
    }
}
