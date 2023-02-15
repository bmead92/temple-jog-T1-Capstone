package com.game.templejog.gui.top;

import com.game.templejog.Game;
import com.game.templejog.Item;
import com.game.templejog.Player;

import javax.swing.*;


public class InventoryMenu {

    public static void setUpInventoryDisplay(Game game) {
        StringBuilder line = new StringBuilder("█  Inventory: ");
        line.append(game.getPlayer().getInventory());
//        for (Item item : game.getPlayer().getInventory()) {
//            line.append(String.format("[%s] ", item.getName()));
//        }
        JFrame inventoryFrame = new JFrame("Inventory");
        JTextArea inventoryMessage = new JTextArea(String.valueOf(line));
        JOptionPane.showMessageDialog(inventoryFrame, inventoryMessage);
    }
}
