package com.game.templejog.gui;

import com.game.templejog.Game;
import com.game.templejog.Sound;

import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SettingsMenu {
    public static boolean activeWindow = false;
    // frame
    public static JFrame settingsFrame;
    // slider
    static JSlider musicSlider;

    public static void settingsMenuDisplay(Game game) {
        activeWindow = true;
        /*DONE: use value to reduce sound*/
        // paint the ticks and tracks
        // create a new frame
        settingsFrame = new JFrame("Settings");
        settingsFrame.setLocationRelativeTo(null);
        // create a panel
        JPanel settingsPanel = new JPanel();
        JLabel settingsLabel = new JLabel();
        // create a slider

        musicSlider = new JSlider(0, 10, 10);

        settingsLabel.setText("Volume is set at " + musicSlider.getValue());
        /*TODO: use value to reduce sound*/

//    // paint the ticks and tracks

        musicSlider.setPaintTrack(true);
        musicSlider.setPaintTicks(true);
        musicSlider.setPaintLabels(true);
        settingsLabel.setText("Volume is set at " + musicSlider.getValue());
        // set spacing
        musicSlider.setMajorTickSpacing(2);
        musicSlider.setMinorTickSpacing(1);
        Clip clip = Sound.getClip();
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        musicSlider.addChangeListener(e -> {
            switch(musicSlider.getValue()) {
                case 5:
                    gainControl.setValue(-10);
                    break;
                case 4:
                    gainControl.setValue(-15);
                    break;
                case 3:
                    gainControl.setValue(-20);
                    break;
                case 2:
                    gainControl.setValue(-25);
                    break;
                case 1:
                    gainControl.setValue(-30);
                    break;
                default:
                    gainControl.setValue(-10);
            }
            settingsLabel.setText("Volume is set at " + musicSlider.getValue());
        });
        // add slider to panel
        settingsPanel.add(settingsLabel);
        settingsPanel.add(musicSlider);
        //DONE:add music On/Off buttons or toggle
        JButton musicToggle = new JButton("Music On/Off");
        BooleanControl muteControl =
                (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
        musicToggle.addActionListener(e -> {
            Sound.stopSound();
            if (game.getPlaySound()) {
                muteControl.setValue(true);
                game.setPlaySound(false);
            } else {
                muteControl.setValue(false);
                game.setPlaySound(true);
                Sound.themeSound(game.getCurrentRoom().getSound());
            }
        });
        settingsPanel.add(musicToggle);
        settingsFrame.add(settingsPanel);
        // set the size of frame
        settingsFrame.setSize(300, 300);
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                activeWindow = false;
            }
        });
        settingsFrame.setVisible(true);
    }
}
