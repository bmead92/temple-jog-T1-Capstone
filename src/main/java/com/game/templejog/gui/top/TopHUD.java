package com.game.templejog.gui.top;

import com.game.templejog.Game;

import javax.swing.*;

public class TopHUD {
    private Game game;
    private JPanel topHUDJPanel;
    private JLabel currentLocation;
    private JLabel currentHealth;
    private final JButton helpButton;
    private final JButton quitButton;

    public TopHUD(Game game) {
        this.topHUDJPanel = new JPanel();
        this.game = game;
        this.currentLocation =  new JLabel();
        this.currentHealth = new JLabel();
        this.helpButton = new JButton("Help");
        helpButton.addActionListener(e -> {
            if (e.getSource() == this.helpButton) {
                HelpMenu.setUpHelpGUI(this.game);
            }
        });
        this.quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            if (e.getSource() == this.quitButton) {
                QuitMenu.setUpQuitOptions();
            }
        });
    }

    public JPanel setUpTopHUDJPanel() {
        currentLocation.setText(game.getCurrentRoom().getName());
        topHUDJPanel.add(currentLocation);
        currentHealth.setText(String.valueOf(game.getPlayer().getHealth()));
        topHUDJPanel.add(this.currentHealth);
        topHUDJPanel.add(this.helpButton);
        topHUDJPanel.add(this.quitButton);
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

    public JPanel getTopHUDJPanel() {
        return topHUDJPanel;
    }

    public void setTopHUDJPanel(JPanel topHUDJPanel) {
        this.topHUDJPanel = topHUDJPanel;
    }
}
