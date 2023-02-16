package com.game.templejog.gui.bottom;

import com.game.templejog.Game;
import com.game.templejog.Item;
import com.game.templejog.gui.MainContainer;

import javax.swing.*;
import java.util.List;

public class SearchMenu {
    public static void setUpSearchDisplay(Game game) {
        List<Item> playerInventory = game.getPlayer().getInventory();
        StringBuilder items = new StringBuilder();
        List<String> itemsInCurrentRoom = game.getCurrentRoom().getItems();
        for (String item : itemsInCurrentRoom) {
            items.append("a ").append(item).append(", ");
            playerInventory.add(game.getItems().get(item));
            game.getPlayer().setInventory(playerInventory);
        }
        String itOrThem = "it";
        if (itemsInCurrentRoom.size() > 1) {
            itOrThem = "them";
        }
        JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
        bottomRightSectionJPanel.removeAll();
        JLabel searchDisplayJLabel;
        String message = "You find nothing here";
        if (itemsInCurrentRoom.size() >= 1) {
            message = String.format("You search the area finding %sand add %s to your inventory", items, itOrThem);
        }
        searchDisplayJLabel = new JLabel(message);
        itemsInCurrentRoom.clear();
        bottomRightSectionJPanel.add(searchDisplayJLabel);
        bottomRightSectionJPanel.validate();
        bottomRightSectionJPanel.repaint();
    }
}
