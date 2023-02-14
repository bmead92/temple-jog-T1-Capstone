package com.game.templejog.gui;

import com.game.templejog.Game;
import com.game.templejog.Temple;
import com.game.templejog.client.FileLoader;

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

        exitButton.addActionListener(e -> {
            if (e.getSource() == exitButton) {
                ExitMenu.setUpExitGUI(gui);
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

        // middle panel w/ image of location
        JPanel middlePanel = new JPanel();
        //TODO: replace with current location image, give each room an image field
        JLabel middleImage = new JLabel(game.getCurrentRoom().getDescription());
        middlePanel.add(middleImage);
        // add panel w/ image to main container
        mainContainer.add(middlePanel, BorderLayout.CENTER);

        // bottom section of main frame
        JPanel bottomSection = new JPanel(new BorderLayout());
        JPanel bottomLeftPanel = new JPanel();
        BoxLayout bottomLeftLayout = new BoxLayout(bottomLeftPanel, 2);
        bottomLeftPanel.setLayout(bottomLeftLayout);
        // bottom left options
        JButton attackButton = new JButton("Attack");
        JButton searchAreaButton = new JButton("Search Area");
        JButton mapButton = new JButton("Map");
        JButton inventoryButton = new JButton("Inventory");
        bottomLeftPanel.add(attackButton);
        bottomLeftPanel.add(searchAreaButton);
        bottomLeftPanel.add(mapButton);
        bottomLeftPanel.add(inventoryButton);
        // add bottom left to main container
        bottomSection.add(bottomLeftPanel, BorderLayout.LINE_START);

        // bottom right dynamic box of info
        JPanel bottomRightDisplay = new JPanel();
        //TODO: Populate br display
        bottomSection.add(bottomRightDisplay, BorderLayout.LINE_END);
        // add bottom right to main container
        mainContainer.add(bottomSection, BorderLayout.PAGE_END);

        mainContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainContainer.setVisible(true);
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
}
