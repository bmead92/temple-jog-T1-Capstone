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
    private JPanel middleSectionPanel, buttonPanel, imagePanel;
    private JLabel backgroundImageJLabel, encounterLabel;
    private ImageIcon currentLocationBackgroundIcon, currentLocationEncounterIcon;
    private JButton upButton, downButton, leftButton, rightButton;
    private Image currentLocationBackgroundImage;

    private AnimatedEncounterPanel animatedEncounterPanel;


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

        animatedEncounterPanel.stopTimer();
        buttonPanel.remove(animatedEncounterPanel);
        // Add the panels to the middleHUD
        if (game.getCurrentRoom().getEncounters_to().size() > 0) {
            JPanel bottomRightSectionJPanel = MainContainer.getBottomSection().getBottomRightSection().getBottomRightSectionJPanel();
            String encounterKey = game.getCurrentRoom().getEncounters_to().get(0);
            String encounterDescription = game.getEncounters().get(encounterKey).getShortDescription();
            bottomRightSectionJPanel.add(new JLabel(encounterDescription));

            currentLocationEncounterIcon = getEncounterIcon();
            animatedEncounterPanel = new AnimatedEncounterPanel(currentLocationEncounterIcon
                    , currentLocationBackgroundImage);
//            encounterLabel.setIcon(currentLocationEncounterIcon);

//            backgroundImageJLabel.setLayout(null);
//            encounterLabel.setBounds(50, 50, 100, 100);
//            backgroundImageJLabel.setIcon(currentLocationEncounterIcon);
//            imagePanel.add(backgroundImageJLabel);
//            buttonPanel.add(imagePanel, BorderLayout.CENTER);
        } else {
//            backgroundImageJLabel.setIcon(currentLocationBackgroundIcon);
//            imagePanel.add(backgroundImageJLabel);
            animatedEncounterPanel = new AnimatedEncounterPanel(currentLocationBackgroundImage);
//            backgroundImageJLabel.setLayout(null);
//            backgroundImageJLabel.setIcon(currentLocationBackgroundIcon);
        }

        buttonPanel.add(animatedEncounterPanel, BorderLayout.CENTER);

        //TODO: check from encounters same way
        middleSectionPanel.add(buttonPanel, BorderLayout.CENTER);
        // Set panel properties
        this.middleSectionPanel.setSize(500, 500);
        this.middleSectionPanel.setVisible(true);

        return this.middleSectionPanel;
    }

//    public void paint(Graphics g) {
//        Graphics2D g2D = (Graphics2D) g;
//        g2D.drawImage(currentLocationBackgroundImage, 0, 0, null);
//        g2D.drawImage(currentLocationEncounterImage, 0, 0, null);
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        if(x>=200-currentLocationEncounterImage.getWidth(null) || x<0) {
//            xVelocity = xVelocity * -1;
//        }
//        x = x + xVelocity;
//
//        if(y>=200-currentLocationEncounterImage.getHeight(null) || y<0) {
//            yVelocity = yVelocity * -1;
//        }
//        y = y + yVelocity;
//        middleSectionPanel.repaint();
//
////        repaint();
//    }

    public ImageIcon getBackgroundIcon() {
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
}
