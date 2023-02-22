package com.game.templejog.gui.middle;

import com.game.templejog.gui.GUIClient;

import java.awt.*;

import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

public class EncounterPanel extends JPanel implements ActionListener{

    final int PANEL_WIDTH = 500;
    final int PANEL_HEIGHT = 500;
    Image enemy;
    Image backgroundImage;
    Timer timer;
    ImageIcon encounterIcon;
    ImageIcon backgroundIcon;
    int xVelocity = 1;
    int yVelocity = 1;
    int x = 0;
    int y = 0;

    EncounterPanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.black);

        enemy = new ImageIcon("src/Images/Encounters/Lion.jpg").getImage();
        backgroundImage = new ImageIcon("src/Images/Rooms/LZ.jpeg").getImage();

        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {

        super.paint(g); // paint background

        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(backgroundImage, 0, 0, null);
        g2D.drawImage(enemy, x, y, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(x>=PANEL_WIDTH-enemy.getWidth(null) || x<0) {
            xVelocity = xVelocity * -1;
        }
        x = x + xVelocity;

        if(y>=PANEL_HEIGHT-enemy.getHeight(null) || y<0) {
            yVelocity = yVelocity * -1;
        }
        y = y + yVelocity;
        repaint();
    }
}