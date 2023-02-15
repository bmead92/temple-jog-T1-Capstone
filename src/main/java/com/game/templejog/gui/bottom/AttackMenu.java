package com.game.templejog.gui.bottom;

import com.game.templejog.Game;
import com.game.templejog.client.Main;
import com.game.templejog.gui.GUI;
import com.game.templejog.gui.MainContainer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AttackMenu {
    public static void setUpAttackMenu(Game game) {
        JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
        JPanel attackMenu = new JPanel();
        JLabel encounterInformation;
        List<String> whenEnteringEncounters = game.getCurrentRoom().getEncounters_to();
        if (whenEnteringEncounters.isEmpty()) {
            bottomRightSectionJPanel.removeAll();
            encounterInformation = new JLabel("There is no danger here right now.");
            attackMenu.add(encounterInformation);
            attackMenu.setBounds(0, 0, GUI.GAME_WIDTH / 4, GUI.GAME_HEIGHT / 4);
            bottomRightSectionJPanel.add(attackMenu);
            bottomRightSectionJPanel.validate();
            bottomRightSectionJPanel.repaint();
        } else {
            bottomRightSectionJPanel.removeAll();
            encounterInformation = new JLabel(String.format("As you enter the %s, you are attacked by a %s.", game.getCurrentRoom().getName(), whenEnteringEncounters.get(0)));
            attackMenu.add(encounterInformation);
            attackMenu.setBounds(0, 0, GUI.GAME_WIDTH / 4, GUI.GAME_HEIGHT / 4);
            bottomRightSectionJPanel.add(attackMenu);
            bottomRightSectionJPanel.validate();
            bottomRightSectionJPanel.repaint();
        }
    }
}
