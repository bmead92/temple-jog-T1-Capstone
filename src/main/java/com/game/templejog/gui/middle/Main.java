package com.game.templejog.gui.middle;

import javax.swing.*;
import java.awt.*;

public class Main{

    public static void main(String[] args) {

        EncounterFrame encounterFrame = new EncounterFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        EncounterPanel encounterPanel = new EncounterPanel();

        encounterFrame.add(encounterPanel);
        encounterFrame.setVisible(true);
    }
}