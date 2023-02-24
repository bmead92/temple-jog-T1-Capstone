package com.game.templejog.gui.bottom;

import com.game.templejog.Game;
import com.game.templejog.gui.MainContainer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchMenu {
    public static void setUpSearchDisplay(Game game) {
        JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
        List<String> itemsInCurrentRoom = game.getCurrentRoom().getItems();
        if (itemsInCurrentRoom.size() > 0) {
            JFrame searchSubMenu = new JFrame("Loot");
            searchSubMenu.setLayout(new FlowLayout());
            searchSubMenu.setSize(250, 100);
            searchSubMenu.setLocationRelativeTo(null);
            searchSubMenu.setVisible(true);
            for (String item : itemsInCurrentRoom) {
                // create a button for each item
                JButton buttonToAdd = new JButton(item);
                searchSubMenu.add(buttonToAdd);
                // if button is pressed, loot the item, add to inventory
                buttonToAdd.addActionListener(e -> {
                    if (e.getSource() == buttonToAdd) {
                        String[] choice = new String[]{"get", item};
                        // processChoice  calls processGetting, which adds the item to the user inventory and
                        // deletes the item from the current room
                        String message = game.processChoice(choice);
                        // remove button when selected
                        searchSubMenu.remove(buttonToAdd);
                        searchSubMenu.validate();
                        searchSubMenu.repaint();
                        JLabel searchDisplayJLabel = new JLabel(message);
                        bottomRightSectionJPanel.removeAll();
                        // display message to bottom right corner
                        bottomRightSectionJPanel.add(searchDisplayJLabel);
                        bottomRightSectionJPanel.validate();
                        bottomRightSectionJPanel.repaint();
                    }
                });
            }
            JButton backButton = new JButton("Back");
            searchSubMenu.add(backButton);
            backButton.addActionListener(e -> {
                if (e.getSource() == backButton) {
                    searchSubMenu.dispose();
                }
            });
        } else {
            String[] choice = new String[]{"get", ""};
            String message = game.processChoice(choice);
            JLabel searchDisplayJLabel = new JLabel(message);
            bottomRightSectionJPanel.removeAll();
            bottomRightSectionJPanel.add(searchDisplayJLabel);
        }
    }
}
