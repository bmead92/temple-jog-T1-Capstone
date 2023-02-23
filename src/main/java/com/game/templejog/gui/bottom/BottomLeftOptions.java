package com.game.templejog.gui.bottom;

import com.game.templejog.Game;
import com.game.templejog.gui.MainContainer;

import javax.swing.*;
import java.awt.*;

import static com.game.templejog.gui.MainContainer.MAIN_CONTAINER_HEIGHT;
import static com.game.templejog.gui.MainContainer.MAIN_CONTAINER_WIDTH;

public class BottomLeftOptions {
    private final Game game;
    private final JButton attackButton;
    private final JButton searchAreaButton;
    private final JButton mapButton;
    private final JButton inventoryButton;
    private final JPanel bottomLeftOptions;
    private final JPanel bottomLeftAttackMap;
    private final JPanel bottomLeftSearchInventory;


    public BottomLeftOptions(Game game) {
        this.game = game;
        this.bottomLeftOptions = new JPanel();
        bottomLeftOptions.setSize(MAIN_CONTAINER_WIDTH / 4, MAIN_CONTAINER_HEIGHT / 6);
        bottomLeftOptions.setBackground(new Color(5, 23, 38));
        this.bottomLeftAttackMap = new JPanel();
        bottomLeftAttackMap.setLayout(new BoxLayout(bottomLeftAttackMap, BoxLayout.PAGE_AXIS));
        bottomLeftAttackMap.setBackground(new Color(5, 23, 38));
        this.bottomLeftSearchInventory = new JPanel();
        bottomLeftSearchInventory.setLayout(new BoxLayout(bottomLeftSearchInventory, BoxLayout.PAGE_AXIS));
        bottomLeftSearchInventory.setBackground(new Color(5, 23, 38));
        this.attackButton = new JButton("Use");
        attackButton.addActionListener(e -> {
            if (e.getSource() == this.attackButton) {
                UseMenu.setUpUseMenu(this.game);
            }
        });
        this.searchAreaButton = new JButton("Search Area");
        searchAreaButton.addActionListener(e -> {
            if (e.getSource() == this.searchAreaButton) {
                SearchMenu.setUpSearchDisplay(this.game);
            }
        });
        this.mapButton = new JButton("Map");
        //DONE: add map actionListener
        //Map button with action listener
        mapButton.addActionListener(e -> {
            if (e.getSource() == this.mapButton) {
                if (MapMenu.clickTracking == false) {
                    MapMenu.mapDisplay(this.game);
                } else {
                    MapMenu.mapFrame.dispose();
                    MapMenu.clickTracking = false;
                }
            }
        });

        this.inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(e -> {
            if (e.getSource() == this.inventoryButton) {
                InventoryMenu.setUpInventoryDisplay(this.game);
            }
        });
    }

    public JPanel setUpBottomLeftOptionsJPanel() {
        bottomLeftAttackMap.add(this.attackButton);
        bottomLeftSearchInventory.add(this.searchAreaButton);
        bottomLeftAttackMap.add(this.mapButton);
        bottomLeftSearchInventory.add(this.inventoryButton);
        bottomLeftOptions.add(bottomLeftAttackMap);
        bottomLeftOptions.add(bottomLeftSearchInventory);
        return bottomLeftOptions;
    }
}
