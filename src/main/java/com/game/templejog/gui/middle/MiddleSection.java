package com.game.templejog.gui.middle;

import com.game.templejog.Encounter;
import com.game.templejog.Game;
import com.game.templejog.Room;
import com.game.templejog.gui.GUIClient;
import com.game.templejog.gui.RunGUI;
import com.game.templejog.gui.MainContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.*;
import java.util.List;

public class MiddleSection {
    private final Game game;
    private JPanel middleSectionPanel;
    private JPanel imagePanel;
    private final JPanel buttonPanel;
    private JLabel backgroundImageJLabel;
    private ImageIcon currentLocationBackgroundIcon;
    private JButton upButton, downButton, leftButton, rightButton;
    private JLabel encounterLabel;
    private ImageIcon currentLocationEncounterIcon;

    public MiddleSection(Game game) {
        this.game = game;
        this.middleSectionPanel = new JPanel();
        this.imagePanel = new JPanel();
        this.backgroundImageJLabel = new JLabel();
        // Create the button panel
        this.buttonPanel = new JPanel(new BorderLayout());
        this.leftButton = new JButton("<");
        this.rightButton = new JButton(">");
        this.upButton = new JButton("^");
        this.downButton = new JButton("v");
        this.encounterLabel = new JLabel();

        // Add button listeners
        leftButton.addActionListener(e -> {
            String[] commands = new String[]{"go", "west"};
            // handle left button click
            this.game.processChoice(commands);
            String roomDescription = this.game.getCurrentRoom().getShortDescription();
            JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
            bottomRightSectionJPanel.removeAll();
            bottomRightSectionJPanel.add(new JLabel(roomDescription));
            setUpMiddleSectionJPanel();
            MainContainer.getTopHUD().setUpTopHUDJPanel();
        });
        rightButton.addActionListener(e -> {
            String[] commands = new String[]{"go", "east"};
            // handle right button click
            this.game.processChoice(commands);
            String roomDescription = this.game.getCurrentRoom().getShortDescription();
            JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
            bottomRightSectionJPanel.removeAll();
            bottomRightSectionJPanel.add(new JLabel(roomDescription));
            setUpMiddleSectionJPanel();
            MainContainer.getTopHUD().setUpTopHUDJPanel();
        });
        upButton.addActionListener(e -> {
            String[] commands = new String[]{"go", "north"};
            // handle up button click
            this.game.processChoice(commands);
            String roomDescription = this.game.getCurrentRoom().getShortDescription();
            JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
            bottomRightSectionJPanel.removeAll();
            bottomRightSectionJPanel.add(new JLabel(roomDescription));
            setUpMiddleSectionJPanel();
            //DONE: Update HUD when changing locations
            MainContainer.getTopHUD().setUpTopHUDJPanel();
        });
        downButton.addActionListener(e -> {
            String[] commands = new String[]{"go", "south"};
            // handle down button click
            this.game.processChoice(commands);
            String roomDescription = this.game.getCurrentRoom().getShortDescription();
            JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
            bottomRightSectionJPanel.removeAll();
            bottomRightSectionJPanel.add(new JLabel(roomDescription));
            setUpMiddleSectionJPanel();
            MainContainer.getTopHUD().setUpTopHUDJPanel();
        });
    }

    public JPanel setUpMiddleSectionJPanel() {
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(leftButton, BorderLayout.WEST);
        buttonPanel.add(rightButton, BorderLayout.EAST);
        buttonPanel.add(upButton, BorderLayout.NORTH);
        buttonPanel.add(downButton, BorderLayout.SOUTH);

        currentLocationBackgroundIcon = getBackgroundImage();
        backgroundImageJLabel.setIcon(currentLocationBackgroundIcon);
        backgroundImageJLabel.setBounds(0, 0, 500, 500);
        // Add the panels to the middleHUD
        imagePanel.add(backgroundImageJLabel, BorderLayout.CENTER);
        buttonPanel.add(this.imagePanel, BorderLayout.CENTER);

        if (game.getCurrentRoom().getEncounters_to().size() > 0) {
            currentLocationEncounterIcon = getEncounterImageTo();
            encounterLabel.setIcon(currentLocationEncounterIcon);
            encounterLabel.setBounds(0, 0, 250, 250);
            imagePanel.add(encounterLabel, BorderLayout.CENTER);
            buttonPanel.add(imagePanel, BorderLayout.CENTER);
        }
        //TODO: check from encounters same way
        middleSectionPanel.add(buttonPanel, BorderLayout.CENTER);

        // Set panel properties
        this.middleSectionPanel.setSize(500, 500);
        this.middleSectionPanel.setVisible(true);

        return this.middleSectionPanel;
    }

    public ImageIcon getBackgroundImage() {
        String currentLocationImage = game.getCurrentRoom().getImage();
        currentLocationBackgroundIcon = null;
        try {
            currentLocationBackgroundIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull
                    (GUIClient.class.getClassLoader().getResourceAsStream(currentLocationImage))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return currentLocationBackgroundIcon;
    }

    public ImageIcon getEncounterImageTo() {
        String encounter = game.getCurrentRoom().getEncounters_to().get(0);
        String encounterImagePath = game.getEncounters().get(encounter).getImage();
        currentLocationEncounterIcon = null;
        try {
            //DONE: inputStream is returning null
            currentLocationEncounterIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull
                    (GUIClient.class.getClassLoader().getResourceAsStream(encounterImagePath))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        return currentLocationEncounterIcon;
    }

    public ImageIcon getEncounterImageFrom() {
        ImageIcon currentLocationEncounterIcon = new ImageIcon();

        Room currentRoom = game.getCurrentRoom();
        List<String> encounterFromList = currentRoom.getEncounters_from();
        if (encounterFromList.isEmpty()) {
            return null;
        }

        String currentEncounter = encounterFromList.get(0);
        HashMap<String, Encounter> mapOfEncounters = game.getEncounters();

        Encounter encounter = null;
        String encounterImagePath = null;

        if (mapOfEncounters.get(currentEncounter) != null) {
            encounter = mapOfEncounters.get(currentEncounter);
        }

        if (encounter != null) {
            encounterImagePath = encounter.getImage();
            System.out.println(encounterImagePath);
            try {
                ImageIcon imageIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(RunGUI.class.getClassLoader().getResourceAsStream(encounterImagePath))));
            } catch (IOException e) {
                throw new RuntimeException(e);
                //TODO: Fix null pointer
            } catch (NullPointerException ignored) {
            }
            currentLocationEncounterIcon = new ImageIcon(encounterImagePath);
        }
        return currentLocationEncounterIcon;
    }
}
