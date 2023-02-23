package com.game.templejog.gui.middle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AnimationPanel extends JPanel implements ActionListener {
    Image image;
    AnimationPanel panel;

    public AnimationPanel(Image image){
        this.setOpaque(false);
        this.image = image;
        Timer timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}