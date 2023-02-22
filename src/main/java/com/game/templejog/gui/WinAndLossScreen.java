package com.game.templejog.gui;

import com.game.templejog.Game;
import com.game.templejog.Sound;
import com.game.templejog.Temple;
import com.game.templejog.client.FileLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WinAndLossScreen {
    private Game game;
    private JFrame winAndLossFrame;
    private JTextArea winAndLossTextArea;
    private JButton confirmationButton;

    private JFrame mainContainer;
    public WinAndLossScreen(Game game, JFrame mainContainer) {
        this.game = game;
        this.winAndLossFrame = new JFrame();
        this.winAndLossTextArea = new JTextArea();
        this.confirmationButton = new JButton();
        this.mainContainer = mainContainer;
        this.confirmationButton.addActionListener(e -> {
            // close this menu, close the game, send to title screen.
            //TODO: link back to title screen
            this.winAndLossFrame.dispose();
            Sound.stopSound();
            mainContainer.dispose();
            Temple gameFiles = null;
            try {
                gameFiles = FileLoader.jsonLoader("JSON/gameFiles.json");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Game newGame = new Game(gameFiles);
            RunGUI runGui = new RunGUI(newGame);
            TitleScreen sm = new TitleScreen(runGui);
            sm.gameStartScreen(newGame);
            Sound.themeSound("sounds/background_music.wav");
        });
    }

    public JFrame setUpWinAndLossScreen() {
        final String outOfTime = "You look down as your alarm goes off. It's 18:00....";
        final String outOfTimeJSON = game.getGameText().get("outOfTime");
        final String outOfLife = game.getGameText().get("outOfLife");
        final String playerDiesMessage = "You look down and realize you are covered in blood...";
        final String successMessage = "You manage to jog back to the landing zone just as the helicopter lands. " +
                "You all take off, eager to get as much distance between yourselves and the temple as possible...";
        final String sortOfWinTime = game.getGameText().get("sortOfWinTime");
        final String sortOfWinDeath = game.getGameText().get("sortOfWinDeath");
        final String win = game.getGameText().get("winText");
        boolean playerOutOfHealth = game.getPlayer().getHealth() <= 0;
        boolean playerOutOfSteps = game.getPlayer().getSteps() >= 24;
        if (game.getCommunicatorOff()) {
            Sound.stopSound();
            this.confirmationButton.setText("Hmm. At least I saved the world.");
            this.winAndLossFrame.setTitle("YOU (sort of) WIN!");
            if (playerOutOfHealth) {
                if (game.getPlaySound()) {
                    Sound.ending("sounds/explosion.wav");
                }
                this.winAndLossTextArea.setText(String.format("%s\n%s", playerDiesMessage, sortOfWinDeath));
            } else if(playerOutOfSteps) {
                if (game.getPlaySound()) {
                    Sound.ending("sounds/explosion.wav");
                }
                this.winAndLossTextArea.setText(String.format("%s\n%s", outOfTime, sortOfWinTime));
             }else {
                if (game.getPlaySound()) {
                    Sound.ending("sounds/victory.wav");
                }
                this.winAndLossTextArea.setText(String.format("%s\n%s", successMessage, win));
                this.winAndLossFrame.setTitle("YOU WIN!");
                this.confirmationButton.setText("Nice.");
            }
        } else {
            this.confirmationButton.setText("Damn, okay.");
            if (playerOutOfHealth) {
                if (game.getPlaySound()) {
                    Sound.stopSound();
                    Sound.ending("sounds/explosion.wav");
                }
                this.winAndLossTextArea.setText(String.format("%s\n%s", playerDiesMessage, outOfLife));
                this.winAndLossFrame.setTitle("YOU DIED!");
            } else if (playerOutOfSteps) {
                if (game.getPlaySound()) {
                    Sound.stopSound();
                    Sound.ending("sounds/explosion.wav");
                }
                this.winAndLossTextArea.setText(String.format("%s\n%s", outOfTime, outOfTimeJSON));
                this.winAndLossFrame.setTitle("YOU RAN OUT OF TIME!");
            }
        }
        this.winAndLossTextArea.setLineWrap(true);
        this.winAndLossTextArea.setWrapStyleWord(true);
        this.winAndLossFrame.setLayout(new BorderLayout());
        winAndLossFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.winAndLossFrame.add(this.winAndLossTextArea, BorderLayout.CENTER);
        this.winAndLossFrame.add(this.confirmationButton, BorderLayout.PAGE_END);
        winAndLossFrame.setSize(400, 250);
        winAndLossFrame.setLocationRelativeTo(null);
        mainContainer.dispose();
        this.winAndLossFrame.setVisible(true);
        return this.winAndLossFrame;
    }
}
