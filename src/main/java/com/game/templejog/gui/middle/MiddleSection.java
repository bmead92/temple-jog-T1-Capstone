package com.game.templejog.gui.middle;

import com.game.templejog.Game;
import com.game.templejog.gui.GUIClient;
import com.game.templejog.gui.MainContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MiddleSection {
    private final Game game;
    private JPanel middleSectionPanel;
    private final JPanel buttonPanel;
    private JPanel imagePanel;
    private JLabel backgroundImageJLabel;
    private ImageIcon currentLocationBackgroundIcon, currentLocationEncounterIcon;
    private final JButton upButton;
    private final JButton downButton;
    private final JButton leftButton;
    private final JButton rightButton;
    private AnimatedEncounterPanel animatedEncounterPanel;

    public MiddleSection(Game game) {
        this.game = game;
        this.middleSectionPanel = new JPanel();
        this.middleSectionPanel.setBackground(new Color(5, 23, 38));
        this.imagePanel = new JPanel();
        JPanel encounterPanel = new JPanel();
        this.backgroundImageJLabel = new JLabel();
        JLabel encounterLabel = new JLabel();

        // Create the button panel
        this.buttonPanel = new JPanel(new BorderLayout());
        this.leftButton = new JButton("<");
        this.rightButton = new JButton(">");
        this.upButton = new JButton("^");
        this.downButton = new JButton("v");

        // Add button listeners
        leftButton.addActionListener(e -> {
            String[] commands = new String[]{"go", "west"};
            // handle left button click
            String cantGoDirection = game.processChoice(commands);
            String roomDescription = game.getCurrentRoom().getShortDescription();
            JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
            bottomRightSectionJPanel.removeAll();
            bottomRightSectionJPanel.add(new JLabel(cantGoDirection));
            bottomRightSectionJPanel.add(new JLabel(roomDescription));
            middleSectionPanel.remove(imagePanel);
            setUpMiddleSectionJPanel();
            MainContainer.getTopHUD().setUpTopHUDJPanel();
        });
        rightButton.addActionListener(e -> {
            String[] commands = new String[]{"go", "east"};
            // handle right button click
            String cantGoDirection = game.processChoice(commands);
            String roomDescription = game.getCurrentRoom().getShortDescription();
            JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
            bottomRightSectionJPanel.removeAll();
            bottomRightSectionJPanel.add(new JLabel(cantGoDirection));
            bottomRightSectionJPanel.add(new JLabel(roomDescription));
            setUpMiddleSectionJPanel();
            MainContainer.getTopHUD().setUpTopHUDJPanel();
        });
        upButton.addActionListener(e -> {
            String[] commands = new String[]{"go", "north"};
            // handle up button click
            String cantGoDirection = game.processChoice(commands);
            String roomDescription = game.getCurrentRoom().getShortDescription();
            JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
            bottomRightSectionJPanel.removeAll();
            bottomRightSectionJPanel.add(new JLabel(cantGoDirection));
            bottomRightSectionJPanel.add(new JLabel(roomDescription));
            setUpMiddleSectionJPanel();
            //DONE: Update HUD when changing locations
            MainContainer.getTopHUD().setUpTopHUDJPanel();
        });
        downButton.addActionListener(e -> {
            String[] commands = new String[]{"go", "south"};
            // handle down button click
            String cantGoDirection = game.processChoice(commands);
            String roomDescription = game.getCurrentRoom().getShortDescription();
            JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
            bottomRightSectionJPanel.removeAll();
            bottomRightSectionJPanel.add(new JLabel(cantGoDirection));
            bottomRightSectionJPanel.add(new JLabel(roomDescription));
            setUpMiddleSectionJPanel();
            MainContainer.getTopHUD().setUpTopHUDJPanel();
        });
        this.animatedEncounterPanel = new AnimatedEncounterPanel();
    }

    public JPanel setUpMiddleSectionJPanel() {
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(leftButton, BorderLayout.WEST);
        buttonPanel.add(rightButton, BorderLayout.EAST);
        buttonPanel.add(upButton, BorderLayout.NORTH);
        buttonPanel.add(downButton, BorderLayout.SOUTH);

        currentLocationBackgroundIcon = getBackgroundImage();
        Image currentLocationBackgroundImage = currentLocationBackgroundIcon.getImage();

        animatedEncounterPanel.stopTimer();
        buttonPanel.remove(animatedEncounterPanel);
        // Add the panels to the middleHUD
        backgroundImageJLabel.setIcon(currentLocationBackgroundIcon);

        if (game.getCurrentRoom().getEncounters_to().size() > 0) {
            JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
            String encounterKey = game.getCurrentRoom().getEncounters_to().get(0);
            String encounterDescription = game.getEncounters().get(encounterKey).getShortDescription();
            bottomRightSectionJPanel.add(new JLabel(encounterDescription));
            String encounterType = game.getEncounters().get(encounterKey).getType();
            if (encounterType.equalsIgnoreCase("enemy")) {
                currentLocationEncounterIcon = getEncounterIcon();
                animatedEncounterPanel = new AnimatedEncounterPanel(currentLocationEncounterIcon
                        , currentLocationBackgroundImage);
            } else {
                animatedEncounterPanel = new AnimatedEncounterPanel(currentLocationBackgroundImage);
            }
        } else {
            animatedEncounterPanel = new AnimatedEncounterPanel(currentLocationBackgroundImage);
        }

        buttonPanel.add(animatedEncounterPanel, BorderLayout.CENTER);

        // Set panel properties
        this.middleSectionPanel.add(buttonPanel, BorderLayout.CENTER);
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

    public ImageIcon getEncounterIcon() {
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

    public JPanel getMiddleSectionPanel() {
        return middleSectionPanel;
    }
}
