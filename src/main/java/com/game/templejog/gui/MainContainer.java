package com.game.templejog.gui;

import com.game.templejog.Game;
import com.game.templejog.SettingsMenu;
import com.game.templejog.gui.bottom.BottomSection;
import com.game.templejog.gui.middle.MiddleSection;
import com.game.templejog.gui.top.QuitMenu;
import com.game.templejog.gui.top.TopHUD;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainContainer {
    public static final int MAIN_CONTAINER_WIDTH = 1200;
    public static final int MAIN_CONTAINER_HEIGHT = 1000;
    private Game game;
    private static TopHUD topHUD;
    private static MiddleSection middleSection;
    private static BottomSection bottomSection;
    private static JFrame mainContainer;
    private JMenuItem saveItem;
    private JMenuItem loadItem;

    public MainContainer(Game game, TopHUD topHUD, MiddleSection middleSection, BottomSection bottomSection) {
        // main container
        mainContainer = new JFrame();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.setSize(MAIN_CONTAINER_WIDTH, MAIN_CONTAINER_HEIGHT);
        mainContainer.setLocationRelativeTo(null);
        this.game = game;
        MainContainer.topHUD = topHUD;
        MainContainer.middleSection = middleSection;
        MainContainer.bottomSection = bottomSection;
    }

    public void setUpMainContainer() {
        JMenuBar fileMenuBar = new JMenuBar();
        JMenu settingsMenu = new JMenu("FILE");
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
        JMenuItem settingsItem = new JMenuItem("SETTINGS");
        settingsItem.addActionListener(e -> {
            if (SettingsMenu.activeWindow) {
                SettingsMenu.settingsFrame.dispose();
                SettingsMenu.activeWindow = false;
            } else {
                SettingsMenu.settingsMenuDisplay(game);
            }
        });
        JMenuItem quitItem = new JMenuItem("QUIT GAME");
        quitItem.addActionListener(e -> {
            QuitMenu.setUpInGameQuitOptions(MainContainer.getMainContainer());
        });
        settingsMenu.add(saveItem);
        settingsMenu.add(loadItem);
        settingsMenu.add(settingsItem);
        settingsMenu.add(quitItem);
        fileMenuBar.add(settingsMenu);
        fileMenuBar.setBorder(BorderFactory.createBevelBorder(3));
        mainContainer.setJMenuBar(fileMenuBar);
        mainContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainContainer.add(topHUD.setUpTopHUDJPanel(), BorderLayout.PAGE_START);
        mainContainer.add(middleSection.setUpMiddleSectionJPanel(), BorderLayout.CENTER);
        mainContainer.add(bottomSection.setUpBottomSectionJPanel(), BorderLayout.PAGE_END);
        mainContainer.setResizable(false);
        mainContainer.setVisible(true);
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

    public static TopHUD getTopHUD() {
        return topHUD;
    }

    public static void setTopHUD(TopHUD t) {
        topHUD = t;
    }

    public static MiddleSection getMiddleSection() {
        return middleSection;
    }

    public void setMiddleSection(MiddleSection middleSection) {
        this.middleSection = middleSection;
    }

    public static BottomSection getBottomSection() {
        return bottomSection;
    }

    public void setBottomSection(BottomSection bottomSection) {
        MainContainer.bottomSection = bottomSection;
    }

    public static JFrame getMainContainer() {
        return mainContainer;
    }

    public void setMainContainer(JFrame mainContainer) {
        this.mainContainer = mainContainer;
    }

}
