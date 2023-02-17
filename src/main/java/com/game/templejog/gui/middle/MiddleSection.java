package com.game.templejog.gui.middle;

import com.game.templejog.Encounter;
import com.game.templejog.Game;
import com.game.templejog.Room;

import com.game.templejog.gui.GUIMain;
import com.game.templejog.gui.MainContainer;

import javax.swing.*;
import java.awt.*;

import java.util.Objects;
import java.util.List;

public class MiddleSection {
    private final Game game;
    private JPanel middleSectionPanel;
    private JPanel imagePanel;
    private final JPanel buttonPanel;
    private JLabel imageLabel;
    private ImageIcon currentLocationBackgroundIcon;
    private JButton upButton, downButton, leftButton, rightButton;

    public MiddleSection(Game game) {
        this.game = game;
        this.middleSectionPanel = new JPanel();
        this.imagePanel = new JPanel();
        this.imageLabel = new JLabel();
        // Create the button panel
        this.buttonPanel = new JPanel(new BorderLayout());
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
            //DONE: Update HUD when changing locations
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
        ImageIcon getEncounterImage = getEncounterImageTo();
        JLabel encounterLabel = new JLabel();
        encounterLabel.setIcon(getEncounterImage);

        leftButton.setLocation(this.imagePanel.getX() - 10, this.imagePanel.getY());
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(leftButton, BorderLayout.WEST);
        buttonPanel.add(rightButton, BorderLayout.EAST);
        buttonPanel.add(upButton, BorderLayout.NORTH);
        buttonPanel.add(downButton, BorderLayout.SOUTH);
        encounterLabel.setIcon(getEncounterImage);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        this.currentLocationBackgroundIcon = getBackgroundImage();
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

    public ImageIcon getBackgroundImage() {
        String currentLocationImage = game.getCurrentRoom().getImage();
        ImageIcon currentLocationBackgroundIcon = new ImageIcon(Objects.requireNonNull
                (GUIMain.class.getClassLoader().getResource(currentLocationImage)));
        return currentLocationBackgroundIcon;
    }

    public ImageIcon getEncounterImageTo() {
        String currentRoomName = game.getCurrentRoom().getName();
        List<String> encounterToList = game.getCurrentRoom().getEncounters_to();

        ImageIcon currentLocationEncounterIcon = new ImageIcon();
        try {
            String currentLocationEncounter = encounterToList.get(0);
            currentLocationEncounterIcon = new ImageIcon(Objects.requireNonNull
                    (GUIMain.class.getClassLoader().getResource(currentLocationEncounter)));
        } catch (NullPointerException e) {
            System.out.println("No encounters here null.");
        } catch (IndexOutOfBoundsException i) {
            System.out.println("No encounters here index.");
        }
        return currentLocationEncounterIcon;
    }

//    public ImageIcon getEncounterImageFrom(Room room) {
//        String currentRoomName = room.getName();
//        List<String> allEncounters = new ArrayList<>();
//        List<String> encounterToList = game.getCurrentRoom().getEncounters_to();
//        List<String> encounterFromList = game.getCurrentRoom().getEncounters_from();
//        allEncounters.addAll(encounterFromList);
//        allEncounters.addAll(encounterToList);
//        System.out.println(allEncounters);
//
//
//        String currentLocationEncounter = encounter.getImage();
//        ImageIcon currentLocationEncounterIcon = new ImageIcon(Objects.requireNonNull
//                (GUI.class.getClassLoader().getResource(currentLocationEncounter)));
//        System.out.println("inside getEncounterImage: " + currentLocationEncounterIcon);
//        return currentLocationEncounterIcon;
//    }
}
