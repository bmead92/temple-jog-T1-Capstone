package com.game.templejog.gui;

import com.game.templejog.Game;
import com.game.templejog.Sound;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class IntroScreen {

    private static JFrame introScreen;
    private static RunGUI runGUI;

    public IntroScreen(RunGUI runGUI) {
        IntroScreen.runGUI = runGUI;
    }

    public static void gameIntroScreen(Game game) {
        introScreen = new JFrame();
        introScreen.setSize(800, 800);
        introScreen.setLocationRelativeTo(null);
        introScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introScreen.setLayout(null);

        JPanel bgIntroPanel = new JPanel();
        bgIntroPanel.setSize(800, 800);
        bgIntroPanel.setBackground(new Color(5, 23, 38));

        JLabel imageMARADMIN = new JLabel();
        imageMARADMIN.setBounds(25, 0, 800, 700);
        ImageIcon introIcon = null;
        try {
            introIcon = new ImageIcon(ImageIO.read(
                    Objects.requireNonNull(IntroScreen.class.getClassLoader()
                            .getResourceAsStream("Images/report_TJ_game.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        imageMARADMIN.setIcon(introIcon);
        imageMARADMIN.setBackground(new Color(5, 23, 38));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(300, 650, 200, 75);
        buttonPanel.setBackground(new Color(5, 23, 38));
        JButton parachuteCut = new JButton("Click to Parachute into LZ");
        parachuteCut.setBackground(new Color(129, 255, 217));
        parachuteCut.setOpaque(true);
        parachuteCut.setBorderPainted(false);
        parachuteCut.addActionListener(e -> {
            Sound.stopSound();
            introScreen.dispose();
            if (game.getPlaySound()) {
                Sound.themeSound(game.getCurrentRoom().getSound());
            }
            new Thread(runGUI::runGame).start();
        });


        buttonPanel.add(parachuteCut);
        introScreen.add(imageMARADMIN);
        introScreen.add(buttonPanel);
        introScreen.add(bgIntroPanel);
        introScreen.setVisible(true);
    }

}
