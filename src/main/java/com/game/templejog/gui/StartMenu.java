package com.game.templejog.gui;

import com.game.templejog.Game;
import com.game.templejog.gui.top.ExitMenu;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;


public class StartMenu {
    JFrame startWindow;
//    Container con;
    JPanel titleNamePanel, startButtonPanel, buttonPanel, bgPanel;
    JLabel titleNameLabel, musicQuestion;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font questionFont = new Font("Times New Roman", Font.PLAIN, 12);
    JButton startButton, exitButton, settingsButton, creditsButton;


    public void gameStartScreen(){
        //create JFrame to hold start menu and title
        startWindow = new JFrame();
        startWindow.setSize(800,800);
        startWindow.setLocationRelativeTo(null);
        startWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startWindow.setLayout(null);

        //create image as background
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
//        titleNamePanel = new JPanel();
//        titleNamePanel.setBounds(100, 100, 600, 150);
//        titleNamePanel.setBackground(Color.darkGray);
//
//        titleNameLabel = new JLabel("TEMPLE JOG");
//        titleNameLabel.setForeground(Color.lightGray);
//        titleNameLabel.setFont(titleFont);

        //create Start button Panel
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 500, 200, 75);
        startButtonPanel.setBackground(Color.lightGray);

        //create start button
        startButton = new JButton("START");
        startButton.setBackground(Color.white);
        startButton.setLocation(100,100);
        /*TODO: connect to start game loop to call start of game show you are at LZ*/

        //create Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setBounds(300, 600, 200, 100);
        buttonPanel.setBackground(Color.lightGray);

        //create settings buttons
        settingsButton = new JButton("SETTINGS");
        settingsButton.setBackground(Color.white);
        /*TODO: add settings for music, music volume. etc*/


        //create exit button
        exitButton = new JButton("EXIT");
        exitButton.setBackground(Color.white);
        /*DONE: add action listener to exit*/
        exitButton.addActionListener(e -> {
            if (e.getSource() == this.exitButton) {
                ExitMenu.setUpExitOptions();
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
        buttonPanel.add(settingsButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(creditsButton);

//        titleNamePanel.add(titleNameLabel);
//        startWindow.add(titleNamePanel);

        startWindow.add(startButtonPanel);
        startWindow.add(buttonPanel);

        startWindow.setVisible(true);

    }


    public static void creditMenu() {
        JFrame helpFrame = new JFrame("Developers Menu");
        JTextArea helpMessage = new JTextArea("Temple Jog Capstone\n\nDevelopers:\nBryce Meadors, Joe Savella, Cindy Pottin\n\nAdaptation from Text Based Game Developed by:\nJoe Racke, Lorenzo Ortega, and Lok Tamang");
        JOptionPane.showMessageDialog(helpFrame, helpMessage, "Developers", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        StartMenu sm = new StartMenu();
        sm.gameStartScreen();

    }

}
