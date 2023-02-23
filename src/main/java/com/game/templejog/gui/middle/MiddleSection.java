package com.game.templejog.gui.middle;

import com.game.templejog.Encounter;
import com.game.templejog.Game;
import com.game.templejog.gui.GUIClient;
import com.game.templejog.gui.MainContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class MiddleSection {
    private final Game game;
<<<<<<< Updated upstream
    private JPanel middleSectionPanel, buttonPanel, imagePanel;
    private JLabel backgroundImageJLabel, encounterLabel;
    private ImageIcon currentLocationBackgroundIcon, currentLocationEncounterIcon;
=======
    private JPanel middleSectionPanel;
    private JPanel imagePanel, encounterPanel;
    private final JPanel buttonPanel;
    private JLabel backgroundImageJLabel;
    private ImageIcon currentLocationBackgroundIcon;
>>>>>>> Stashed changes
    private JButton upButton, downButton, leftButton, rightButton;
    private Image currentLocationBackgroundImage;
<<<<<<< Updated upstream

    private AnimatedEncounterPanel animatedEncounterPanel;

=======
    private JFrame encounterFrame;
    int xVelocity = 1;
    int yVelocity = 1;
    int x = 0;
    int y = 0;
>>>>>>> Stashed changes

    public MiddleSection(Game game) {
        this.game = game;
        this.middleSectionPanel = new JPanel();
        this.middleSectionPanel.setBackground(new Color(5, 23, 38));
        this.imagePanel = new JPanel();
        this.encounterPanel = new JPanel();
        this.backgroundImageJLabel = new JLabel();
        this.encounterLabel = new JLabel();
//        this.encounterPanel = new AnimationPanel(currentLocationEncounterImage);

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
            this.game.processChoice(commands);
            String roomDescription = this.game.getCurrentRoom().getShortDescription();
            JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
            bottomRightSectionJPanel.removeAll();
            bottomRightSectionJPanel.add(new JLabel(roomDescription));
            middleSectionPanel.remove(imagePanel);
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
            middleSectionPanel.remove(imagePanel);
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
        this.animatedEncounterPanel = new AnimatedEncounterPanel();
    }

    public JPanel setUpMiddleSectionJPanel() {
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(leftButton, BorderLayout.WEST);
        buttonPanel.add(rightButton, BorderLayout.EAST);
        buttonPanel.add(upButton, BorderLayout.NORTH);
        buttonPanel.add(downButton, BorderLayout.SOUTH);
        currentLocationBackgroundIcon = getBackgroundIcon();
        currentLocationBackgroundImage = currentLocationBackgroundIcon.getImage();
<<<<<<< Updated upstream

        animatedEncounterPanel.stopTimer();
        buttonPanel.remove(animatedEncounterPanel);
        // Add the panels to the middleHUD
=======
        backgroundImageJLabel.setIcon(currentLocationBackgroundIcon);
>>>>>>> Stashed changes

        if (game.getCurrentRoom().getEncounters_to().size() > 0) {
            JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
            String encounterKey = game.getCurrentRoom().getEncounters_to().get(0);
            String encounterDescription = game.getEncounters().get(encounterKey).getShortDescription();
            bottomRightSectionJPanel.add(new JLabel(encounterDescription));
<<<<<<< Updated upstream

            String encounterType = game.getEncounters().get(encounterKey).getType();
            if (encounterType.equalsIgnoreCase("enemy")) {
            currentLocationEncounterIcon = getEncounterIcon();
            animatedEncounterPanel = new AnimatedEncounterPanel(currentLocationEncounterIcon
                    , currentLocationBackgroundImage);
            } else {
                animatedEncounterPanel = new AnimatedEncounterPanel(currentLocationBackgroundImage);
            }
        }
        else {
            animatedEncounterPanel = new AnimatedEncounterPanel(currentLocationBackgroundImage);
        }

        buttonPanel.add(animatedEncounterPanel, BorderLayout.CENTER);

        //DONE: check from encounters same way
=======
            currentLocationEncounterIcon = getEncounterImage();
            currentLocationEncounterImage = currentLocationEncounterIcon.getImage();
            encounterLabel.setIcon(currentLocationEncounterIcon);
        }

        imagePanel.add(backgroundImageJLabel);
        encounterPanel.add(encounterLabel);

        buttonPanel.setBounds(0,0, 500,500);
        buttonPanel.add(imagePanel);
//        buttonPanel.add(encounterPanel);
        // Add the panels to the middleHUD

>>>>>>> Stashed changes
        middleSectionPanel.add(buttonPanel, BorderLayout.CENTER);
        // Set panel properties
        this.middleSectionPanel.setSize(500, 500);
        this.middleSectionPanel.setVisible(true);

        return this.middleSectionPanel;
    }

<<<<<<< Updated upstream
    public ImageIcon getBackgroundIcon() {
=======
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(currentLocationBackgroundImage, 0, 0, null);
        g2D.drawImage(currentLocationEncounterImage, 0, 0, null);
    }

    public void actionPerformed(ActionEvent e) {
        if (x >= 200 - currentLocationEncounterImage.getWidth(null) || x < 0) {
            xVelocity = xVelocity * -1;
        }
        x = x + xVelocity;

        if (y >= 200 - currentLocationEncounterImage.getHeight(null) || y < 0) {
            yVelocity = yVelocity * -1;
        }
        y = y + yVelocity;
        middleSectionPanel.repaint();

//        repaint();
    }

    public ImageIcon getBackgroundImage() {
>>>>>>> Stashed changes
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
