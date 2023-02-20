//package com.game.templejog.gui.top;
//
//import com.game.templejog.Game;
//
//import javax.swing.*;
//
//public class PauseMenu {
//    static JMenu pauseMenu;
//    static JMenuItem saveItem;
//    static JMenuItem loadItem;
//    static JMenuItem settingsItem;
//    static JMenuItem quitItem;
//
//    public static void JMenu pauseMenu() {
//        pauseMenu = new JMenu();
//        saveItem = new JMenuItem("SAVE");
//        saveItem.addActionListener(e -> {
//            if (e.getSource() == this.saveItem) {
//                saveGame(game);
//            }
//        });
//
//        loadItem = new JMenuItem("LOAD");
//        loadItem.addActionListener(e -> {
//            if (e.getSource() == this.loadItem) {
//                loadGame(game);
//            }
//        });
//
//        settingsItem = new JMenuItem("SETTINGS");
//        settingsItem.addActionListener(e -> {
//            if (e.getSource() == this.settingsItem) {
//                ExitMenu.setUpExitOptions();
//            }
//        });
//
//        quitItem = new JMenuItem("QUIT GAME");
//        quitItem.addActionListener(e -> {
//            if (e.getSource() == this.quitItem) {
//                ExitMenu.setUpExitOptions();
//            }
//        });
//
//        pauseMenu.add(saveItem);
//        pauseMenu.add(loadItem);
//        pauseMenu.add(settingsItem);
//        pauseMenu.add(quitItem);
//
//        return pauseMenu;
//    }
//}