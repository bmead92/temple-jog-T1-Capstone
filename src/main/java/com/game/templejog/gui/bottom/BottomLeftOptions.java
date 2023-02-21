package com.game.templejog.gui.bottom;

import com.game.templejog.Game;

import javax.swing.*;

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
        this.bottomLeftAttackMap = new JPanel();
        bottomLeftAttackMap.setLayout(new BoxLayout(bottomLeftAttackMap, BoxLayout.PAGE_AXIS));
        this.bottomLeftSearchInventory = new JPanel();
        bottomLeftSearchInventory.setLayout(new BoxLayout(bottomLeftSearchInventory, BoxLayout.PAGE_AXIS));
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
            if(e.getSource() == this.mapButton){
                MapMenu.mapDisplay(this.game);
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
