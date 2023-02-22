package com.game.templejog.gui.top;

import com.game.templejog.Game;
import com.game.templejog.client.Main;
import com.game.templejog.gui.MainContainer;
import com.game.templejog.gui.bottom.MapMenu;

import javax.swing.*;
import java.io.*;

public class TopHUD {
    private Game game;
    private JPanel topHUDJPanel;
    private JLabel currentLocation;
    private JLabel currentHealth;
    private JProgressBar currentHealthBar;
    private JLabel hoursPlayed;
    private final JButton helpButton;

    public TopHUD(Game game) {
        this.topHUDJPanel = new JPanel();
        this.game = game;
        this.currentLocation = new JLabel();
        this.currentHealth = new JLabel();
        this.currentHealthBar = new JProgressBar(0, 90);
        this.hoursPlayed = new JLabel();
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
        currentHealth.setText(String.valueOf("     HP: " + game.getPlayer().getHealth()));
        currentHealthBar.setValue(game.getPlayer().getHealth() * 10);
        int playTime = game.getPlayer().getSteps()*15;
        int playHrs = playTime/60;
        int playMins = playTime%60;
        hoursPlayed.setText("   TIME: " + (1200 + (100*playHrs) + playMins));
        topHUDJPanel.add(this.currentHealth);
        topHUDJPanel.add(this.currentHealthBar);
        topHUDJPanel.add(this.hoursPlayed);
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
