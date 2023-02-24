package com.game.templejog.gui.top;

import com.game.templejog.Game;

import javax.swing.*;
import java.awt.*;

public class TopHUD {
    private final JButton helpButton;
    private Game game;
    private JPanel topHUDJPanel;
    private JLabel currentLocation;
    private JLabel currentHealth;
    private JProgressBar currentHealthBar;
    private JLabel timeLabel;

    public TopHUD(Game game) {
        this.topHUDJPanel = new JPanel();
        this.topHUDJPanel.setBackground(new Color(5, 23, 38));
        this.game = game;
        this.currentLocation = new JLabel();
        this.currentLocation.setForeground(Color.WHITE);
        this.currentHealth = new JLabel();
        this.currentHealthBar = new JProgressBar(0, 90);
        this.currentHealth.setForeground(Color.WHITE);
        this.timeLabel = new JLabel();
        this.timeLabel.setForeground(Color.WHITE);
        this.helpButton = new JButton("Help");
        helpButton.addActionListener(e -> {
            if (e.getSource() == this.helpButton) {
                HelpMenu.setUpHelpGUI(this.game);
            }
        });
    }

    public JPanel setUpTopHUDJPanel() {
        currentLocation.setText(game.getCurrentRoom().getName());
        topHUDJPanel.add(currentLocation);
        currentHealth.setText("     HP: " + game.getPlayer().getHealth());
        currentHealthBar.setValue(game.getPlayer().getHealth() * 10);
        int playTime = game.getPlayer().getSteps() * 15;
        int playHrs = playTime / 60;
        int playMins = playTime % 60;
        timeLabel.setText("   TIME: " + (1200 + (100 * playHrs) + playMins));
        topHUDJPanel.add(this.currentHealth);
        topHUDJPanel.add(this.currentHealthBar);
        topHUDJPanel.add(this.timeLabel);
        topHUDJPanel.add(this.helpButton);
        topHUDJPanel.setVisible(true);
        return topHUDJPanel;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public JPanel getTopHUDJPanel() {
        return topHUDJPanel;
    }

}
