package com.game.templejog.gui.bottom;

import com.game.templejog.Game;
import com.game.templejog.Item;
import com.game.templejog.gui.MainContainer;

import javax.swing.*;
import java.util.List;

public class SearchMenu {
    public static void setUpSearchDisplay(Game game) {
        JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
        bottomRightSectionJPanel.removeAll();
        JLabel searchDisplayJLabel;
        String items = "";
        String currentRoomDescription = game.getCurrentRoom().getDescription();
        List<String> currentRoomItems = game.getCurrentRoom().getItems();
        List<Item> playerInventory = game.getPlayer().getInventory();
        if (currentRoomItems.size() == 0) {
            searchDisplayJLabel = new JLabel(String.format("%s \nYou find nothing useful here.", currentRoomDescription));
        } else {
            for (String item : currentRoomItems) {
                items += item + ", ";
                Item poppedItem = game.popItemFromMap(item);
                playerInventory.add(poppedItem);
            }
            currentRoomItems.clear();
            searchDisplayJLabel = new JLabel(String.format("%s \nYou find %sand add it to you inventory.", currentRoomDescription, items));
        }
        bottomRightSectionJPanel.add(searchDisplayJLabel);
        bottomRightSectionJPanel.validate();
        bottomRightSectionJPanel.repaint();
    }
}
