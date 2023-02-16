package com.game.templejog.gui.top;

import com.game.templejog.Game;
import com.game.templejog.gui.MainContainer;

import javax.swing.*;

public class TopHUD {
    private Game game;

    private JPanel topHUDJPanel;
    private JLabel currentLocation;
    private JLabel currentHealth;
    private final JButton helpButton;
    private final JButton exitButton;

    public TopHUD(Game game) {
        this.game = game;
        this.currentLocation =  new JLabel();
        this.currentHealth = new JLabel();
        this.helpButton = new JButton("Help");
        helpButton.addActionListener(e -> {
            if (e.getSource() == this.helpButton) {
                HelpMenu.setUpHelpGUI(this.game);
            }
        });
        this.exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            if (e.getSource() == this.exitButton) {
                ExitMenu.setUpExitOptions();
            }
        });
    }

    public JPanel setUpTopHUDJPanel() {
        topHUDJPanel = new JPanel();
        currentLocation = new JLabel("Location: " + game.getCurrentRoom().getName());
        topHUDJPanel.add(currentLocation);
        currentHealth = new JLabel("Health: " + game.getPlayer().getHealth());
        topHUDJPanel.add(currentHealth);
        topHUDJPanel.add(helpButton);
        topHUDJPanel.add(exitButton);
        topHUDJPanel.setVisible(true);
        return topHUDJPanel;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public JLabel getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(JLabel currentLocation) {
        this.currentLocation = currentLocation;
    }

    public JLabel getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(JLabel currentHealth) {
        this.currentHealth = currentHealth;
    }
}
