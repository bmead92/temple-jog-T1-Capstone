package com.game.templejog.gui.top;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class SettingsMenu{
    // frame
    static JFrame settingsFrame;
    // slider
    static JSlider musicSlider;


    public static void settingsMenuDisplay(){
    // create a new frame
    settingsFrame = new JFrame("Settings");

    // create a panel
    JPanel settingsPanel = new JPanel();
    // create a slider
    musicSlider = new JSlider(0, 10, 10);

//    // paint the ticks and tracks
        musicSlider.setPaintTrack(true);
        musicSlider.setPaintTicks(true);
        musicSlider.setPaintLabels(true);
//
    // set spacing
        musicSlider.setMajorTickSpacing(5);
        musicSlider.setMinorTickSpacing(1);


    // add slider to panel
        settingsPanel.add(musicSlider);

        //TODO:add music On/Off buttons or toggle
JToggleButton musicToggle = new JToggleButton("Music");
        ItemListener musicItemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int musicState = e.getStateChange();

                if(musicState == e.SELECTED){
                    System.out.println("Music On");
                }
                else{
                    System.out.println("Music Off");
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
