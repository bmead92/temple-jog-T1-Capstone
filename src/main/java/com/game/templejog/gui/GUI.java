package com.game.templejog.gui;

import com.game.templejog.Game;
import com.game.templejog.Temple;
import com.game.templejog.client.FileLoader;
import com.game.templejog.gui.top.ExitMenu;
import com.game.templejog.gui.top.HelpMenu;
import com.game.templejog.gui.top.InventoryMenu;

import javax.swing.*;
import java.awt.*;
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

    public GUI () {

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



    public void guiSetUp(GUI gui) {
        JFrame mainContainer = new JFrame();
        this.mainContainer = mainContainer;
        mainContainer.setBounds(0, 0, GAME_WIDTH, GAME_HEIGHT);
        mainContainer.setLayout(null);

        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(0,0, 800, 800);
        String pathToImage = gui.game.getCurrentRoom().getImage();
        ImageIcon lzImage = new ImageIcon(GUI.class.getClassLoader().getResource(pathToImage));
        JLabel lzImageLabel = new JLabel(lzImage);
        imagePanel.add(lzImageLabel);
        imagePanel.setVisible(true);

        //adding button for available directions to move
        JPanel middlePanel = new JPanel();
        JLabel arrowsLabel = new JLabel();
        arrowsLabel.setBounds(0,0,400,400);
        arrowsLabel.setBackground(Color.BLUE);
        JButton upButton = new JButton("^");
        upButton.setForeground(Color.BLUE);
        arrowsLabel.setLocation(imagePanel.getX(), imagePanel.getY() - 10);
        arrowsLabel.add(upButton);

        JButton downButton = new JButton("v");
        downButton.setForeground(Color.BLUE);
        arrowsLabel.setLocation(imagePanel.getX(), imagePanel.getY() + 10);
        arrowsLabel.add(downButton);

        JButton leftButton = new JButton("<");
        leftButton.setForeground(Color.BLUE);
        arrowsLabel.setLocation(imagePanel.getX() - 10, imagePanel.getY());
        arrowsLabel.add(leftButton);

        JButton rightButton = new JButton(">");
        rightButton.setForeground(Color.BLUE);
        arrowsLabel.setLocation(imagePanel.getX() + 10, imagePanel.getY());
        arrowsLabel.add(rightButton);
        arrowsLabel.setVisible(true);

        imagePanel.add(arrowsLabel);
        middlePanel.add(imagePanel);

        // add middle panel w/ image to main container
        mainContainer.add(imagePanel);
        mainContainer.setVisible(true);
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

        JPanel middlePanel = setUpLocationBackgroundImage();

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
        JButton mapButton = new JButton("Map");

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

        mainContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainContainer.setVisible(true);
    }

    private JPanel setUpLocationBackgroundImage() {
        JPanel imagePanel = new JPanel();
        String currentLocationImage = this.game.getCurrentRoom().getImage();
        ImageIcon currentLocationBackgroundIcon = new ImageIcon(GUI.class.getClassLoader().getResource(currentLocationImage));

        JLabel imageLabel = new JLabel(currentLocationBackgroundIcon);
        imageLabel.setBounds(50,0,700,350);

        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // middle panel w/ image of location
        JPanel middlePanel = new JPanel();
        //TODO: replace with current location image, give each room an image field
        JLabel middleImage = new JLabel(game.getCurrentRoom().getDescription());
        middlePanel.add(middleImage);
        // middle panel w/ location desc
        //TODO: replace with current location image, give each room an image field
        JLabel locationDesc = new JLabel(game.getCurrentRoom().getDescription());
        middlePanel.add(locationDesc);
        middlePanel.add(imagePanel);

        return middlePanel;
    }

    public ImageIcon getBackgroundImage() {
        String currentLocationImage = this.game.getCurrentRoom().getImage();
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
