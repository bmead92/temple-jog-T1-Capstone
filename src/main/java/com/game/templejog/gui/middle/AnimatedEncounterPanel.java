package com.game.templejog.gui.middle;

import com.game.templejog.Game;
import com.game.templejog.Temple;
import com.game.templejog.gui.GUIClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

public class AnimatedEncounterPanel extends JPanel implements ActionListener {
    private static final int PANEL_WIDTH = 1024;
    private static final int PANEL_HEIGHT = 691;
    private static final int DELAY = 25;
    private static final int IMAGE_WIDTH = 50;
    private static final int IMAGE_HEIGHT = 50;

    private int xPosition = 0;
    private int yPosition = 0;
    private int xDirection = 1;
    private int yDirection = 1;
    private ImageIcon imageIcon;
    private Image backgroundImage;
    private Timer timer;

    public AnimatedEncounterPanel() {
    }

    public AnimatedEncounterPanel(Image backgroundImage) {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(new BorderLayout());
        this.backgroundImage = backgroundImage;
//        add(createPanelNoEncounter(), BorderLayout.CENTER);
    }

    public AnimatedEncounterPanel(ImageIcon imageIcon, Image backgroundImage) {
        this(backgroundImage);
        this.imageIcon = imageIcon;
//        add(createPanel(), BorderLayout.CENTER);
        timer = new Timer(DELAY, this);
       timer.start();
    }

    public void stopTimer(){
        if (timer != null) {
            timer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0,0,null);
        if (imageIcon != null) {
            imageIcon.paintIcon(this,g,xPosition,yPosition);
        }
    }

    public JPanel createPanel() {
//        imageIcon = new ImageIcon("src/main/resources/Images/Encounters/Lion.jpg");
//        backgroundImage = new ImageIcon("src/main/resources/Images/Rooms/LZ.jpeg").getImage();
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, this);
                imageIcon.paintIcon(this, g, xPosition, yPosition);
            }
        };

        JLabel bgImageLabel = new JLabel(new ImageIcon(backgroundImage));
        JLabel iconLabel = new JLabel(imageIcon);

        Timer timer = new Timer(DELAY, this);
        timer.start();

        bgImageLabel.setLayout(null);
        iconLabel.setBounds(50, 50, 100, 100);
        bgImageLabel.add(iconLabel);

        return panel;
    }

    public JPanel createPanelNoEncounter() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, this);
            }
        };

        JLabel bgImageLabel = new JLabel(new ImageIcon(backgroundImage));

        Timer timer = new Timer(DELAY, this);
        timer.start();

        bgImageLabel.setLayout(null);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        xPosition += xDirection;
        yPosition += yDirection;

        if (xPosition + IMAGE_WIDTH > PANEL_WIDTH || xPosition < 0) {
            xDirection = -xDirection;
        }

        if (yPosition + IMAGE_HEIGHT > PANEL_HEIGHT || yPosition < 0) {
            yDirection = -yDirection;
        }

        repaint();
    }
}
