package com.game.templejog.gui.middle;

import java.awt.*;

import javax.swing.*;

public class EncounterFrame extends JFrame{

    EncounterPanel panel;

    EncounterFrame(){

        panel = new EncounterPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}