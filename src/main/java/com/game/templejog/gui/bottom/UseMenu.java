package com.game.templejog.gui.bottom;

import com.game.templejog.Game;
import com.game.templejog.Item;
import com.game.templejog.gui.MainContainer;
import com.game.templejog.gui.middle.MiddleSection;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.game.templejog.gui.MainContainer.MAIN_CONTAINER_HEIGHT;
import static com.game.templejog.gui.MainContainer.MAIN_CONTAINER_WIDTH;

public class UseMenu {
    public static void setUpUseMenu(Game game) {
        JPanel attackMenu = new JPanel();
        attackMenu.setBackground(new Color(129,255,217));
        JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
        bottomRightSectionJPanel.removeAll();

        MiddleSection middleSection = MainContainer.getMiddleSection();
        JPanel middleSectionPanel = MainContainer.getMiddleSection().getMiddleSectionPanel();

        JTextArea encounterInformation = new JTextArea();
        encounterInformation.setLineWrap(true);
        encounterInformation.setWrapStyleWord(true);
        encounterInformation.setEditable(false);
        encounterInformation.setOpaque(true);
        encounterInformation.setBackground(new Color(129,255,217));
        encounterInformation.setBounds(0, 0, MAIN_CONTAINER_WIDTH / 2, MAIN_CONTAINER_HEIGHT / 6);
        // if they player has items in their inventory, open an attack submenu and create a button for each inventory item
        // when the button is pressed, game.processChoice is called
        List<Item> playerInventory = game.getPlayer().getInventory();
        if (playerInventory.size() > 0) {
            JFrame attackSubMenu = new JFrame("Use");
            attackSubMenu.setLayout(new FlowLayout());
            attackSubMenu.setSize(250, 100);
            attackSubMenu.setLocationRelativeTo(null);
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
                        bottomRightSectionJPanel.add(attackMenu);
                        bottomRightSectionJPanel.validate();
                        bottomRightSectionJPanel.repaint();

                        //update middlesection panel after successful attack
                        middleSection.setUpMiddleSectionJPanel();
                        middleSectionPanel.revalidate();
                        middleSectionPanel.repaint();

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
