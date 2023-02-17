package com.game.templejog.gui.bottom;

import com.game.templejog.Game;
import com.game.templejog.Item;
import com.game.templejog.gui.MainContainer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UseMenu {
    public static void setUpUseMenu(Game game) {
        JPanel attackMenu = new JPanel();
        JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
        bottomRightSectionJPanel.removeAll();
        // TODO: Make this pretty
        JTextArea encounterInformation = new JTextArea();
        encounterInformation.setLineWrap(true);
        encounterInformation.setWrapStyleWord(true);
        encounterInformation.setBounds(100, 0, 500, 150);
        // if they player has items in their inventory, open an attack submenu and create a button for each inventory item
        // when the button is pressed, game.processChoice is called
        List<Item> playerInventory = game.getPlayer().getInventory();
        if (playerInventory.size() > 0) {
            JFrame attackSubMenu = new JFrame("Use");
            attackSubMenu.setLayout(new FlowLayout());
            attackSubMenu.setBounds(200, 645, 250, 250);
            for (Item item : playerInventory) {
                JButton buttonToAdd = new JButton(item.getName());
                buttonToAdd.addActionListener(e -> {
                    if (e.getSource() == buttonToAdd) {
                        String inventoryItem = item.getName().toLowerCase();
                        final String[] choice = new String[] {"use", inventoryItem};
                        if (item.getReuse() == 0) {
                            attackSubMenu.remove(buttonToAdd);
                            attackSubMenu.validate();
                            attackSubMenu.repaint();
                        }
                        bottomRightSectionJPanel.removeAll();
                        encounterInformation.setText(game.processChoice(choice));
                        attackMenu.add(encounterInformation);
                        attackMenu.setBounds(0, 0, MainContainer.MAIN_CONTAINER_WIDTH / 4, MainContainer.MAIN_CONTAINER_HEIGHT / 4);
                        bottomRightSectionJPanel.add(attackMenu);
                        bottomRightSectionJPanel.validate();
                        bottomRightSectionJPanel.repaint();
                    }
                });
                attackSubMenu.add(buttonToAdd);
            }
            final JButton backButton = new JButton("Back");
            attackSubMenu.add(backButton);
            backButton.addActionListener(e -> {
                if (e.getSource() == backButton) {
                    attackSubMenu.dispose();
                }
            });
            attackSubMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            attackSubMenu.setVisible(true);
        // if user inventory is empty, do not open attack sub menu, and display message in lower right corner
        } else {
            encounterInformation.setText(game.processChoice(new String[]{"use", ""}));
            bottomRightSectionJPanel.add(encounterInformation);
        }
    }
}
