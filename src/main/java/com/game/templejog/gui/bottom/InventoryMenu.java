package com.game.templejog.gui.bottom;

import com.game.templejog.Game;
import com.game.templejog.Item;
import com.game.templejog.Player;

import javax.swing.*;


public class InventoryMenu {

    public static void setUpInventoryDisplay(Game game) {
        StringBuilder line = new StringBuilder("Inventory:\n");
        for (Item item : game.getPlayer().getInventory()) {
            line.append(String.format("%s\n", item.getName()));
        }
        JFrame inventoryFrame = new JFrame("Inventory");
        JTextArea inventoryMessage = new JTextArea(String.valueOf(line));
        JOptionPane.showMessageDialog(inventoryFrame, inventoryMessage);
    }
}
