package com.game.templejog.gui;

import com.game.templejog.gui.top.QuitMenu;
import com.game.templejog.Game;
import com.game.templejog.gui.bottom.BottomSection;
import com.game.templejog.gui.middle.MiddleSection;
import com.game.templejog.Temple;
import com.game.templejog.client.FileLoader;
import com.game.templejog.gui.top.TopHUD;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class StartMenu {
    JFrame startWindow;
//    Container con;
    JPanel titleNamePanel, startButtonPanel, buttonPanel, bgPanel;
    JLabel titleNameLabel, musicQuestion;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 45);
    Font questionFont = new Font("Times New Roman", Font.PLAIN, 12);
    JButton startButton, quitButton, settingsButton, creditsButton, loadButton, saveButton;

    public StartMenu(Game game) throws IOException {
    }

    public void gameStartScreen(Game game){
        //create JFrame to hold start menu and title
        startWindow = new JFrame();
        startWindow.setSize(800,800);
        startWindow.setLocationRelativeTo(null);
        startWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startWindow.setLayout(null);

//        create image as background
//        bgPanel = new JPanel();
//        bgPanel.setBounds(0,100,750,300);
//        bgPanel.setBackground(Color.cyan);
//        bgPanel.setLayout(null);

        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(100,0,600,500);
//        imageLabel.setSize(600,600);

        ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource("Images/temple.jpeg"));
        imageLabel.setIcon(bgIcon);

        startWindow.add(imageLabel);
//        startWindow.add(bgPanel);

//        con = startWindow.getContentPane();

        //Create Title
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 10, 600, 60);
        titleNamePanel.setBackground(Color.darkGray);

        titleNameLabel = new JLabel("TEMPLE JOG");
        titleNameLabel.setForeground(Color.lightGray);
        titleNameLabel.setFont(titleFont);

        //create Start button Panel
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 500, 200, 75);
        startButtonPanel.setBackground(Color.lightGray);

        //create start button
        startButton = new JButton("START");
        startButton.setBackground(Color.white);
        startButton.setLocation(100,100);
        /*TODO: connect to start game loop to call start of game show you are at LZ*/

        //create load button
        loadButton = new JButton("LOAD");
        loadButton.setBackground(Color.white);
        loadButton.addActionListener(e -> {
            if (e.getSource() == this.loadButton) {
                loadGame(game);
            }
        });

        //create Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setBounds(300, 600, 200, 100);
        buttonPanel.setBackground(Color.lightGray);

        //create settings buttons
        settingsButton = new JButton("SETTINGS");
        settingsButton.setBackground(Color.white);
        /*TODO: add settings for music, music volume. etc*/

        //create QUIT button
        quitButton = new JButton("QUIT");
        quitButton.setBackground(Color.white);

        //create exit button
        quitButton = new JButton("EXIT");
        quitButton.setBackground(Color.white);

        /*DONE: add action listener to exit*/
        quitButton.addActionListener(e -> {
            if (e.getSource() == this.quitButton) {
                QuitMenu.setUpQuitOptions();
            }
        });

        //create credits button
        creditsButton = new JButton("CREDITS");
        creditsButton.setBackground(Color.white);
        /*DONE: add dialog information window with credit for codebase and our work*/
        creditsButton.addActionListener(e -> {
            if (e.getSource() == this.creditsButton) {
                creditMenu();
            }
        });

        startButtonPanel.add(startButton);
        startButtonPanel.add(loadButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(creditsButton);

        titleNamePanel.add(titleNameLabel);
        startWindow.add(titleNamePanel);

        startWindow.add(startButtonPanel);
        startWindow.add(buttonPanel);

        startWindow.setVisible(true);
    }

    private static void saveGame(Game game) {
        try {
            FileOutputStream fos = new FileOutputStream("TempleJog.sav");
            ObjectOutput oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.flush();
            oos.close();
            System.out.println("Game saved\n");
        } catch (Exception e) {
            System.out.println("Serialization error: " + e.getClass() + ": " + e.getMessage());
        }
    }

    private static void loadGame(Game game) {
        try {
            FileInputStream fis = new FileInputStream("TempleJog.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game) ois.readObject();
            ois.close();
            System.out.println("\n Game loaded");
        } catch (Exception e) {
            System.out.println("Deserialization error: " + e.getClass() + ": " + e.getMessage());
        }
    }

    public static void creditMenu() {
        JFrame helpFrame = new JFrame("Developers Menu");
        JTextArea helpMessage = new JTextArea("Temple Jog Capstone\n\nDevelopers:\nBryce Meadors, Joe Savella, Cindy Pottin\n\nAdaptation from Text Based Game Developed by:\nJoe Racke, Lorenzo Ortega, and Lok Tamang");
        JOptionPane.showMessageDialog(helpFrame, helpMessage, "Developers", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) throws IOException {
        final Temple gameFiles = FileLoader.jsonLoader("JSON/gameFiles.json");
        final Game game = new Game(gameFiles);

        StartMenu sm = new StartMenu(game);
        sm.gameStartScreen(game);

    }
}
