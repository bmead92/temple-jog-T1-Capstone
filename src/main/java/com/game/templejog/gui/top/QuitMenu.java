package com.game.templejog.gui.top;

import com.game.templejog.Game;
import com.game.templejog.Sound;
import com.game.templejog.Temple;
import com.game.templejog.client.FileLoader;
import com.game.templejog.gui.IntroScreen;
import com.game.templejog.gui.RunGUI;
import com.game.templejog.gui.TitleScreen;

import javax.swing.*;
import java.io.IOException;

public class QuitMenu {
    static JFrame frame;
    public static void setUpTitleScreenQuitOptions() {
        //click event will return an int and we can use that int in the if statement to take action
        int n = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to quit?",
                "Quit Message",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if(n == 0){
            System.exit(0);
        }
    }

    public static void setUpInGameQuitOptions(JFrame mainContainer) {
        JFrame inGameQuitJFrame = new JFrame();
        JButton titleScreenOption = new JButton("Title Screen");
        titleScreenOption.addActionListener(e -> {
            Sound.stopSound();
            inGameQuitJFrame.dispose();
            mainContainer.dispose();
            Temple gameFiles = null;
            try {
                gameFiles = FileLoader.jsonLoader("JSON/gameFiles.json");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Game game = new Game(gameFiles);
            RunGUI runGui = new RunGUI(game);
            TitleScreen sm = new TitleScreen();
            IntroScreen is = new IntroScreen(runGui);
            sm.gameStartScreen(game);
            Sound.themeSound("sounds/background_music.wav");
        });
        JButton deskTopOption = new JButton("Desktop");
        deskTopOption.addActionListener(e -> {
            System.exit(0);
        });
        JOptionPane quitOptionPane = new JOptionPane("     Where would like to to quit to?");
        quitOptionPane.setOptions(new JButton[]{titleScreenOption, deskTopOption});
        quitOptionPane.setVisible(true);
        inGameQuitJFrame.setSize(250, 250);
        inGameQuitJFrame.add(quitOptionPane);
        inGameQuitJFrame.setLocationRelativeTo(null);
        inGameQuitJFrame.setResizable(false);
        inGameQuitJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inGameQuitJFrame.setVisible(true);

    }
}
