package com.game.templejog.gui.bottom;

import com.game.templejog.Game;
import com.game.templejog.gui.top.InventoryMenu;

import javax.swing.*;

public class BottomLeftOptions {
    private final Game game;
    private final JButton attackButton;
    private final JButton searchAreaButton;
    private final JButton mapButton;
    private final JButton inventoryButton;

    public BottomLeftOptions(Game game) {
        this.game = game;
        this.attackButton = new JButton("Attack");
        attackButton.addActionListener(e -> {
            if (e.getSource() == this.attackButton) {
                AttackMenu.setUpAttackMenu(this.game);
            }
        });
        this.searchAreaButton = new JButton("Search Area");
        searchAreaButton.addActionListener(e -> {
            if (e.getSource() == this.searchAreaButton) {
                SearchMenu.setUpSearchDisplay(this.game);
            }
        });
        this.mapButton = new JButton("Map");
        //TODO: add map actionListener

        this.inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(e -> {
            if (e.getSource() == this.inventoryButton) {
                InventoryMenu.setUpInventoryDisplay(this.game);
            }
        });
    }

    public JPanel setUpBottomLeftOptionsJPanel() {
        JPanel bottomLeftOptions = new JPanel();
        JPanel bottomLeftAttackMap = new JPanel();
        bottomLeftAttackMap.setLayout(new BoxLayout(bottomLeftAttackMap, BoxLayout.PAGE_AXIS));
        JPanel bottomLeftSearchInventory = new JPanel();
        bottomLeftSearchInventory.setLayout(new BoxLayout(bottomLeftSearchInventory, BoxLayout.PAGE_AXIS));
        bottomLeftAttackMap.add(this.attackButton);
        bottomLeftSearchInventory.add(this.searchAreaButton);
        bottomLeftAttackMap.add(this.mapButton);
        bottomLeftSearchInventory.add(this.inventoryButton);
        bottomLeftOptions.add(bottomLeftAttackMap);
        bottomLeftOptions.add(bottomLeftSearchInventory);
        return bottomLeftOptions;
    }
}
