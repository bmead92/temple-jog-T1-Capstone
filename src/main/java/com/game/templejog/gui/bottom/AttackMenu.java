package com.game.templejog.gui.bottom;

import com.game.templejog.Game;
import com.game.templejog.Item;
import com.game.templejog.gui.GUIMain;
import com.game.templejog.gui.MainContainer;

import javax.swing.*;
import java.util.List;

public class AttackMenu {
    public static void setUpAttackMenu(Game game) {
        final List<Item> playerInventory = game.getPlayer().getInventory();
        String inventoryItem = "";
        try {
             inventoryItem = playerInventory.get(0).getName();
        } catch (IndexOutOfBoundsException ignored) {}
        final String[] choice = new String[] {"use", inventoryItem};
        JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
        JPanel attackMenu = new JPanel();
        bottomRightSectionJPanel.removeAll();
        JLabel encounterInformation = new JLabel(game.processChoice(choice));
        attackMenu.add(encounterInformation);
        attackMenu.setBounds(0, 0, MainContainer.MAIN_CONTAINER_WIDTH / 4, MainContainer.MAIN_CONTAINER_HEIGHT / 4);
        bottomRightSectionJPanel.add(attackMenu);
        bottomRightSectionJPanel.validate();
        bottomRightSectionJPanel.repaint();
    }
}
