package com.game.templejog.gui;

import com.game.templejog.Game;
import com.game.templejog.Room;
import com.game.templejog.Temple;
import com.game.templejog.client.FileLoader;
import com.game.templejog.gui.bottom.MapMenu;
import com.game.templejog.gui.top.ExitMenu;
import com.game.templejog.gui.top.HelpMenu;
import com.game.templejog.gui.top.InventoryMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 800;
    private Game game;
    private JFrame mainContainer;
    private JPanel topHUD;
    private JLabel currentLocationLabel;
    private JLabel currentHealthLabel;
    private JLabel timeLabel;
    private JPanel middleImage;
    private JPanel bottomLeftOptions;
    private JPanel bottomRightDisplay;
    private JButton directionArrows;
    private JButton attackButton;
    private JButton searchButton;
    private JButton mapButton;
    private JButton inventoryButton;
    private JButton helpButton;
    private JButton exitButton;

    public GUI() {

    }

    public GUI(Game game, JFrame mainContainer, JPanel topHUD, JLabel currentLocationLabel, JLabel currentHealthLabel,
               JLabel timeLabel, JPanel middleImage, JPanel bottomLeftOptions, JPanel bottomRightDisplay,
               JButton directionArrows, JButton attackButton, JButton searchButton, JButton mapButton,
               JButton inventoryButton, JButton helpButton, JButton exitButton) {
        this.game = game;
        this.mainContainer = mainContainer;
        this.topHUD = topHUD;
        this.currentLocationLabel = currentLocationLabel;
        this.currentHealthLabel = currentHealthLabel;
        this.timeLabel = timeLabel;
        this.middleImage = middleImage;
        this.bottomLeftOptions = bottomLeftOptions;
        this.bottomRightDisplay = bottomRightDisplay;
        this.directionArrows = directionArrows;
        this.attackButton = attackButton;
        this.searchButton = searchButton;
        this.mapButton = mapButton;
        this.inventoryButton = inventoryButton;
        this.helpButton = helpButton;
        this.exitButton = exitButton;
    }


    public JPanel setUpMiddleHUD() {
        JLabel imageLabel;
        JButton leftButton, rightButton, upButton, downButton;
        JPanel imagePanel, buttonPanel;

//        JFrame frame = new JFrame();
        JPanel middleHUD = new JPanel();
        JPanel middlePanel = new JPanel();

        imagePanel = new JPanel();
        ImageIcon currentLocationBackgroundIcon = getBackgroundImage();
        String currentRoom = game.getCurrentRoom().getName();
        String image = game.getCurrentRoom().getImage();
        imageLabel = new JLabel(currentLocationBackgroundIcon);
        imageLabel.setIcon(currentLocationBackgroundIcon);
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);

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
        middleHUD.setLayout(new BorderLayout());
        buttonPanel.add(imagePanel, BorderLayout.CENTER);
        middlePanel.add(buttonPanel, BorderLayout.CENTER);
        middleHUD.add(middlePanel, BorderLayout.CENTER);

        // Set panel properties
        middleHUD.setSize(500, 500);
        middleHUD.setVisible(true);

        // Add button listeners
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle left button click
                game.processNavigating("west");
                System.out.println("from actionlistner: " + game.getCurrentRoom().getName());
            }
        });
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle right button click
                game.processNavigating("east");
                System.out.println("from actionlistner: " + game.getCurrentRoom().getName());
            }
        });
        upButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle up button click
                game.processNavigating("north");
                System.out.println("from actionlistner: " + game.getCurrentRoom().getName());
            }
        });
        downButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle down button click
                game.processNavigating("south");
                System.out.println("from actionlistner: " + game.getCurrentRoom().getName());
            }
        });

        return middleHUD;
    }

    public void setUpGUI(GUI gui) {
        // main container
        JFrame mainContainer = new JFrame();
        this.mainContainer = mainContainer;
        mainContainer.setBounds(0, 0, GAME_WIDTH, GAME_HEIGHT);
        // overall layout of main container
        BorderLayout mainFrame = new BorderLayout();
        mainContainer.setLayout(mainFrame);

        // hud layout and panel
        FlowLayout HUD = new FlowLayout();
        JPanel topHUD = new JPanel();

        JPanel middlePanel = setUpMiddleHUD();

        // add middle panel w/ image to main container
        mainContainer.add(middlePanel, BorderLayout.CENTER);

        JLabel currentLocation = setupCurrentLocationLabel();
        JLabel currentHealth = setUpCurrentHealthLabel();
        //Jlabel currentTime = setUpCurrentTimeLabel();

        JButton helpButton = new JButton("Help");
        helpButton.addActionListener(e -> {
            if (e.getSource() == helpButton) {
                HelpMenu.setUpHelpGUI(game);
            }
        });

        JButton exitButton = new JButton("Exit");
        this.exitButton = exitButton;
        exitButton.addActionListener(e -> {
            if (e.getSource() == exitButton) {
              ExitMenu.setUpExitOptions();
            }
        });

        // add information to hud
        topHUD.setLayout(HUD);
        topHUD.add(currentLocation);
        topHUD.add(currentHealth);
        topHUD.add(helpButton);
        topHUD.add(exitButton);

        // add hud to main container
        mainContainer.add(topHUD, BorderLayout.PAGE_START);

        // bottom section of main frame
        JPanel bottomSection = new JPanel(new BorderLayout());
        JPanel bottomLeftPanel = new JPanel();
        BoxLayout bottomLeftLayout = new BoxLayout(bottomLeftPanel, 2);
        bottomLeftPanel.setLayout(bottomLeftLayout);
        // bottom left options
        JButton attackButton = new JButton("Attack");
        attackButton.addActionListener(e -> {
            if (e.getSource() == attackButton) {
//                AttackMenu.setUpAttackMenu(gui, game);
            }
        });
        JButton searchAreaButton = new JButton("Search Area");

        //Map button with action listener
        JButton mapButton = new JButton("Map");
        mapButton.addActionListener(e -> {
            if(e.getSource() == mapButton){
                MapMenu.mapDisplay(game);
            }
        });


//Inventory Button on bottom left panel with action listener
        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(e -> {
            if (e.getSource() == inventoryButton) {
                InventoryMenu.setUpInventoryDisplay(game);
            }
        });
        bottomLeftPanel.add(attackButton);
        bottomLeftPanel.add(searchAreaButton);
        bottomLeftPanel.add(mapButton);
        bottomLeftPanel.add(inventoryButton);
        // add bottom left to main container
        bottomSection.add(bottomLeftPanel, BorderLayout.LINE_START);

        // bottom right dynamic box of info
        JPanel bottomRightDisplay = new JPanel();
        this.bottomRightDisplay = bottomRightDisplay;
        bottomRightDisplay.setBackground(Color.gray);
        //TODO: Populate br display
        bottomSection.add(bottomRightDisplay, BorderLayout.LINE_END);
        // add bottom right to main container
        mainContainer.add(bottomSection, BorderLayout.PAGE_END);

        mainContainer.pack();
        mainContainer.revalidate();
        mainContainer.repaint();
        mainContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainContainer.setVisible(true);
    }

//    private JPanel setUpLocationBackgroundImage() {
//        JPanel imagePanel = new JPanel();
//        String currentLocationImage = this.game.getCurrentRoom().getImage();
//        ImageIcon currentLocationBackgroundIcon = new ImageIcon(GUI.class.getClassLoader().getResource(currentLocationImage));
//
//        JLabel imageLabel = new JLabel(currentLocationBackgroundIcon);
//        imageLabel.setBounds(50, 0, 700, 350);
//
//        imagePanel.add(imageLabel, BorderLayout.CENTER);
//
//        // middle panel w/ image of location
//        JPanel middlePanel = new JPanel();
//        //TODO: replace with current location image, give each room an image field
//        JLabel middleImage = new JLabel(game.getCurrentRoom().getDescription());
//        middlePanel.add(middleImage);
//        // middle panel w/ location desc
//        //TODO: replace with current location image, give each room an image field
//        JLabel locationDesc = new JLabel(game.getCurrentRoom().getDescription());
//        middlePanel.add(locationDesc);
//        middlePanel.add(imagePanel);
//
//        return middlePanel;
//    }

    public ImageIcon getBackgroundImage() {
        String currentLocationImage = game.getCurrentRoom().getImage();
        System.out.println("inside getBackgroundImage: " + currentLocationImage);
        ImageIcon currentLocationBackgroundIcon = new ImageIcon(GUI.class.getClassLoader().getResource(currentLocationImage));
        return currentLocationBackgroundIcon;
    }

    public static void main(String[] args) throws IOException {
        Temple gameFiles = FileLoader.jsonLoader("JSON/gameFiles.json");
        GUI gui = new GUI();
        gui.game = new Game(gameFiles);
        gui.setUpGUI(gui);
    }

    public JLabel setupCurrentLocationLabel() {
        String currentLocation = this.game.getCurrentRoom().getName();
        return new JLabel(currentLocation);
    }

    public JLabel setUpCurrentHealthLabel() {
        Integer playerCurrentHealth = this.game.getPlayer().getHealth();
        return new JLabel(String.valueOf(playerCurrentHealth));
    }

    public JLabel setUpCurrentTimeLabel() {
        //TODO: Capture current time, add it to a JLabel.
        return new JLabel();
    }

    public JButton getHelpButton() {
        return helpButton;
    }

    public JFrame getMainContainer() {
        return this.mainContainer;
    }

    public JPanel getBottomRightDisplay() {
        return bottomRightDisplay;
    }
}
