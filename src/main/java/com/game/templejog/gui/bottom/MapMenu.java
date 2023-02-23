package com.game.templejog.gui.bottom;

import com.game.templejog.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Objects;

public class MapMenu {

    public static boolean clickTracking = false;
    public static JFrame mapFrame;

    public static void mapDisplay(Game game){
        mapFrame = new JFrame("Map - " + game.getCurrentRoom().getName());
        mapFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mapFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                clickTracking = false;
            }
        });
        mapFrame.setSize(800, 800);
        mapFrame.setLocationRelativeTo(null);
        JPanel mapPanel = new JPanel();
        mapPanel.setBounds(0,50,800,800);
        mapPanel.setLayout(null);

        JLabel mapLabel = new JLabel();
        mapLabel.setBounds(50,0,800,800);
        ImageIcon mapImage = null;
        try {
            mapImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(MapMenu.class.getClassLoader().getResourceAsStream("Images/Temple_Jog_map.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mapLabel.setIcon(mapImage);
        mapFrame.add(mapLabel);
        mapFrame.setVisible(true);
        clickTracking = true;
    }
}
