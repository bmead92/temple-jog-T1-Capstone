package com.game.templejog.gui.top;

import com.game.templejog.Game;

import javax.swing.*;
import java.io.*;

public class TopHUD {
    private Game game;
    private JPanel topHUDJPanel;
    private JLabel currentLocation;
    private JLabel currentHealth;
    private JProgressBar currentHealthBar;
    private final JButton helpButton;
    private final JButton quitButton;
    private JMenuBar pauseBar;
    private JMenu pauseMenu;
    private JMenuItem saveItem;
    private JMenuItem quitItem;
    private JMenuItem loadItem;
    private JMenuItem settingsItem;

    public TopHUD(Game game) {
        this.topHUDJPanel = new JPanel();
        this.game = game;
        this.currentLocation =  new JLabel();
        this.currentHealth = new JLabel();
        this.currentHealthBar = new JProgressBar(0,90);
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
        this.pauseBar = new JMenuBar();
        this.pauseMenu = new JMenu();
        pauseBar.add(pauseMenu);
        pauseMenu.addActionListener(e -> {
            if (e.getSource() == this.pauseMenu) {
                pauseMenu(game);
            }
        });
    }

    private void pauseMenu(Game game) {
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
            if (e.getSource() == this.settingsItem) {
                //TODO: create settings method
            }
        });

        quitItem = new JMenuItem("QUIT GAME");
        quitItem.addActionListener(e -> {
            if (e.getSource() == this.quitItem) {
                QuitMenu.setUpQuitOptions();
            }
        });

        pauseMenu.add(saveItem);
        pauseMenu.add(loadItem);
        pauseMenu.add(settingsItem);
        pauseMenu.add(quitItem);
    }

    public JPanel setUpTopHUDJPanel() {
        currentLocation.setText(game.getCurrentRoom().getName());
        topHUDJPanel.add(currentLocation);
        currentHealth.setText(String.valueOf("HP: " + game.getPlayer().getHealth()));
        currentHealthBar.setValue(game.getPlayer().getHealth() * 10);
        topHUDJPanel.add(this.currentHealth);
        topHUDJPanel.add(this.currentHealthBar);
        topHUDJPanel.add(this.helpButton);
        topHUDJPanel.add(this.quitButton);
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
