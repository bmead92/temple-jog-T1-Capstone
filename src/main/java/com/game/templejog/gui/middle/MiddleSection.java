package com.game.templejog.gui.middle;

import com.game.templejog.Game;
import com.game.templejog.Room;
import com.game.templejog.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MiddleSection {
    private Game game;
    private JPanel middleSectionPanel;

    public MiddleSection(Game game) {
        this.game = game;
    }

    public JPanel setUpMiddleSectionJPanel(Room room) {
        JButton leftButton, rightButton, upButton, downButton;
        JPanel imagePanel, buttonPanel;

        this.middleSectionPanel = new JPanel();

        ImageIcon currentLocationBackgroundIcon = getBackgroundImage(room);
        JLabel imageLabel = new JLabel(currentLocationBackgroundIcon);
        imageLabel.setBounds(50,0,700,350);
        imageLabel.validate();
        imageLabel.repaint();

        this.middleSectionPanel.add(imageLabel, BorderLayout.CENTER);

        // middle panel w/ image of location
        this.middleSectionPanel.add(imageLabel);

        imagePanel = new JPanel();
        imagePanel.add(imageLabel, BorderLayout.CENTER);
//        imageLabel = new JLabel(currentLocationBackgroundIcon);
//        imageLabel.setIcon(currentLocationBackgroundIcon);
//        imagePanel.setLayout(new BorderLayout());


        // Create the button panel
        buttonPanel = new JPanel();
        leftButton = new JButton("<");
        leftButton.setLocation(imagePanel.getX() - 10, imagePanel.getY());
        rightButton = new JButton(">");
        upButton = new JButton("^");
        downButton = new JButton("v");
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(leftButton, BorderLayout.WEST);
        buttonPanel.add(rightButton, BorderLayout.EAST);
        buttonPanel.add(upButton, BorderLayout.NORTH);
        buttonPanel.add(downButton, BorderLayout.SOUTH);

        // Add the panels to the middleHUD
        middleSectionPanel.setLayout(new BorderLayout());
        buttonPanel.add(imagePanel, BorderLayout.CENTER);
        middleSectionPanel.add(buttonPanel, BorderLayout.CENTER);

        // Set panel properties
        middleSectionPanel.setSize(500, 500);
        middleSectionPanel.setVisible(true);

        // Add button listeners
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle left button click
                game.processNavigating("west");
                middleSectionPanel = setUpMiddleSectionJPanel(game.getCurrentRoom());
            }
        });
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle right button click
                game.processNavigating("east");
                middleSectionPanel = setUpMiddleSectionJPanel(game.getCurrentRoom());
            }
        });
        upButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle up button click
                game.processNavigating("north");
                middleSectionPanel = setUpMiddleSectionJPanel(game.getCurrentRoom());
            }
        });
        downButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle down button click
                game.processNavigating("south");
                middleSectionPanel = setUpMiddleSectionJPanel(game.getCurrentRoom());
            }
        });

        this.middleSectionPanel.validate();
        this.middleSectionPanel.repaint();
        return this.middleSectionPanel;
    }

    public ImageIcon getBackgroundImage(Room room) {
        String currentLocationImage = room.getImage();
        ImageIcon currentLocationBackgroundIcon = new ImageIcon(Objects.requireNonNull
                (GUI.class.getClassLoader().getResource(currentLocationImage)));
        System.out.println("inside getBackgroundImage: " + currentLocationBackgroundIcon);
        return currentLocationBackgroundIcon;
    }
}
