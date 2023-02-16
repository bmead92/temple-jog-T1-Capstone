package com.game.templejog.gui.middle;

import com.game.templejog.Game;
import com.game.templejog.Room;
import com.game.templejog.gui.GUIMain;
import com.game.templejog.gui.MainContainer;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MiddleSection {
    private final Game game;
    private JPanel middleSectionPanel;
    private JPanel imagePanel;
    private JLabel imageLabel;
    private ImageIcon currentLocationBackgroundIcon;
    private JButton upButton, downButton, leftButton, rightButton;

    public MiddleSection(Game game) {
        this.game = game;
        this.middleSectionPanel = new JPanel();
        this.imagePanel = new JPanel();
        this.imageLabel = new JLabel();
        this.leftButton = new JButton("<");
        this.rightButton = new JButton(">");
        this.upButton = new JButton("^");
        this.downButton = new JButton("v");

        // Add button listeners
        leftButton.addActionListener(e -> {
            String[] commands = new String[] {"go", "west"};
            // handle left button click
            this.game.processChoice(commands);
            setUpMiddleSectionJPanel();
            MainContainer.getTopHUD().setUpTopHUDJPanel();
        });
        rightButton.addActionListener(e -> {
            String[] commands = new String[] {"go", "east"};
            // handle right button click
            this.game.processChoice(commands);
            setUpMiddleSectionJPanel();
            MainContainer.getTopHUD().setUpTopHUDJPanel();
        });
        upButton.addActionListener(e -> {
            String[] commands = new String[] {"go", "north"};
            // handle up button click
            this.game.processChoice(commands);
            setUpMiddleSectionJPanel();
            //TODO: Update HUD when changing locations
            MainContainer.getTopHUD().setUpTopHUDJPanel();
        });
        downButton.addActionListener(e -> {
            String[] commands = new String[] {"go", "south"};
            // handle down button click
            this.game.processChoice(commands);
            setUpMiddleSectionJPanel();
            MainContainer.getTopHUD().setUpTopHUDJPanel();
        });
    }

    public JPanel setUpMiddleSectionJPanel() {
        // Create the button panel
        JPanel buttonPanel = new JPanel(new BorderLayout());
        leftButton.setLocation(this.imagePanel.getX() - 10, this.imagePanel.getY());
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(leftButton, BorderLayout.WEST);
        buttonPanel.add(rightButton, BorderLayout.EAST);
        buttonPanel.add(upButton, BorderLayout.NORTH);
        buttonPanel.add(downButton, BorderLayout.SOUTH);

        this.currentLocationBackgroundIcon = getBackgroundImage(this.game.getCurrentRoom());
        this.imageLabel.setIcon(currentLocationBackgroundIcon);
        this.imageLabel.setBounds(50,0,500,500);

        this.imagePanel.add(this.imageLabel, BorderLayout.CENTER);

        // Add the panels to the middleHUD
        buttonPanel.add(this.imagePanel, BorderLayout.CENTER);
        this.middleSectionPanel.add(buttonPanel, BorderLayout.CENTER);

        // Set panel properties
        this.middleSectionPanel.setSize(500, 500);
        this.middleSectionPanel.setVisible(true);

        return this.middleSectionPanel;
    }

    public ImageIcon getBackgroundImage(Room room) {
        String currentLocationImage = room.getImage();
        ImageIcon currentLocationBackgroundIcon = new ImageIcon(Objects.requireNonNull
                (GUIMain.class.getClassLoader().getResource(currentLocationImage)));
        return currentLocationBackgroundIcon;
    }
}
