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
    //    private final JButton quitButton;
    private JMenuBar pauseBar;
    private JMenu pauseMenu;
    private JMenuItem saveItem;
    private JMenuItem quitItem;
    private JMenuItem loadItem;
    private JMenuItem settingsItem;

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


        this.pauseBar = new JMenuBar();
        this.pauseMenu = new JMenu("FILE");
        saveItem = new JMenuItem("SAVE");
        saveItem.addActionListener(e -> {
            if (e.getSource() == this.saveItem) {
                saveGame(game);
            }
        });


        loadItem = new JMenuItem("LOAD");
        loadItem.addActionListener(e -> {
            if (e.getSource() == this.loadItem) {
                loadGame(game);
            }
        });

        settingsItem = new JMenuItem("SETTINGS");
        settingsItem.addActionListener(e -> {
            if (SettingsMenu.activeWindow) {
                SettingsMenu.settingsFrame.dispose();
                SettingsMenu.activeWindow = false;
            } else {
                SettingsMenu.settingsMenuDisplay(game);
            }
        });

        quitItem = new JMenuItem("QUIT GAME");
        quitItem.addActionListener(e -> {
            QuitMenu.setUpInGameQuitOptions(MainContainer.getMainContainer());
        });

        pauseMenu.add(saveItem);
        pauseMenu.add(loadItem);
        pauseMenu.add(settingsItem);
        pauseMenu.add(quitItem);

        pauseBar.add(pauseMenu);

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
//        topHUDJPanel.add(this.quitButton);
        topHUDJPanel.add(this.pauseBar);
        topHUDJPanel.setVisible(true);
        return topHUDJPanel;
    }

    private static void saveGame(Game game) {
        try {
            FileOutputStream fos = new FileOutputStream("TempleJog.sav");
            ObjectOutput oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.flush();
            oos.close();
            System.out.println("Game saved\n");
        } catch (Exception e) {
            System.out.println("Serialization error: " + e.getClass() + ": " + e.getMessage());
        }
    }

    private static void loadGame(Game game) {
        try {
            FileInputStream fis = new FileInputStream("TempleJog.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game) ois.readObject();
            ois.close();
            System.out.println("\n Game loaded");
        } catch (Exception e) {
            System.out.println("Deserialization error: " + e.getClass() + ": " + e.getMessage());
        }
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
