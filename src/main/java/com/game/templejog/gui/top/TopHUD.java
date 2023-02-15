package com.game.templejog.gui.top;

import com.game.templejog.Game;

import javax.swing.*;

public class TopHUD {
    private Game game;
    private JLabel currentLocation;
    private JLabel currentHealth;
    private final JButton helpButton;
    private final JButton exitButton;

    public TopHUD(Game game) {
        this.game = game;
        this.currentLocation = setupCurrentLocationLabel();
        this.currentHealth = setUpCurrentHealthLabel();
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
        JPanel topHUDJPanel = new JPanel();
        topHUDJPanel.add(this.currentLocation);
        topHUDJPanel.add(this.currentHealth);
        topHUDJPanel.add(helpButton);
        topHUDJPanel.add(exitButton);
        topHUDJPanel.setVisible(true);
        return topHUDJPanel;
    }
    public JLabel setupCurrentLocationLabel() {
        String currentLocation = this.game.getCurrentRoom().getName();
        return new JLabel(currentLocation);
    }

    public JLabel setUpCurrentHealthLabel() {
        Integer playerCurrentHealth = this.game.getPlayer().getHealth();
        return new JLabel(String.valueOf(playerCurrentHealth));
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
