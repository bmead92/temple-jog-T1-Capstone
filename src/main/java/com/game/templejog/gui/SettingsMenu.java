package com.game.templejog.gui;

import com.game.templejog.Game;
import com.game.templejog.Sound;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsMenu {
    public static boolean activeWindow = false;
    // frame
    public static JFrame settingsFrame;
    // slider
    static JSlider musicSlider;
    public static void settingsMenuDisplay(Game game) {
        activeWindow = true;
        // create a new frame
        settingsFrame = new JFrame("Settings");

        settingsFrame.setLocationRelativeTo(null);

        //create an object
        int volume = 10;

        // create a panel
        JPanel settingsPanel = new JPanel();
        JLabel settingsLabel = new JLabel();

        // create a slider
        musicSlider = new JSlider(0, 10, 10);

        settingsLabel.setText("Volume is set at " + musicSlider.getValue());
        /*TODO: use value to reduce sound*/
        // paint the ticks and tracks
        musicSlider.setPaintTrack(true);
        musicSlider.setPaintTicks(true);
        musicSlider.setPaintLabels(true);
        // set spacing
        musicSlider.setMajorTickSpacing(5);
        musicSlider.setMinorTickSpacing(1);
        musicSlider.addChangeListener(e -> {
            settingsLabel.setText("Volume is set at " + musicSlider.getValue());
            //                Sound.themeSound(game.getCurrentRoom().getSound());
        });
        // add slider to panel
        settingsPanel.add(settingsLabel);
        settingsPanel.add(musicSlider);
        //DONE:add music On/Off buttons or toggle
        JToggleButton musicToggle = new JToggleButton("Music On/Off");
        musicToggle.addActionListener(e -> {
            if (game.getPlaySound()) {
                game.setPlaySound(false);
                Sound.stopSound();
            } else {
                game.setPlaySound(true);
                Sound.themeSound(game.getCurrentRoom().getSound());
            }
        });
        settingsPanel.add(musicToggle);
        settingsFrame.add(settingsPanel);
        // set the size of frame
        settingsFrame.setSize(300, 300);
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setVisible(true);
    }
}
