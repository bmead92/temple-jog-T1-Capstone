package com.game.templejog.gui.top;

import com.game.templejog.Game;
import com.game.templejog.Sound;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class SettingsMenu {
    // frame
    static JFrame settingsFrame;
    // slider
    static JSlider musicSlider;


    public static void settingsMenuDisplay(Game game) {
        // create a new frame
        settingsFrame = new JFrame("Settings");

        //create an object
        int volume = 10;

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

        // set spacing
        musicSlider.setMajorTickSpacing(5);
        musicSlider.setMinorTickSpacing(1);
        musicSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                settingsLabel.setText("Volume is set at " + musicSlider.getValue());
//                Sound.themeSound(game.getCurrentRoom().getSound());
            }
        });


        // add slider to panel
        settingsPanel.add(settingsLabel);
        settingsPanel.add(musicSlider);

        //TODO:add music On/Off buttons or toggle
        JToggleButton musicToggle = new JToggleButton("Music On/Off");
        ItemListener musicItemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int musicState = e.getStateChange();

                if (musicState == e.SELECTED) {
                    System.out.println("Music On");
                    game.setPlaySound(true);
                    Sound.themeSound(game.getCurrentRoom().getSound());

                } else {
                    System.out.println("Music Off");
                    Sound.stopSound();
                    game.setPlaySound(false);
                }

            }
        };

        musicToggle.addItemListener(musicItemListener);

        settingsPanel.add(musicToggle);
        settingsFrame.add(settingsPanel);

        // set the size of frame
        settingsFrame.setSize(300, 300);
        settingsFrame.setVisible(true);
    }
}
