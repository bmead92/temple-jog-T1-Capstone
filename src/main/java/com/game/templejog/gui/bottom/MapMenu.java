package com.game.templejog.gui.bottom;

import com.game.templejog.Game;

import javax.swing.*;

public class MapMenu {

    public static void mapDisplay(Game game){
        final JFrame mapFrame = new JFrame("Map - " + game.getCurrentRoom().getName());
        mapFrame.setBounds(0,0,800,800);
        JPanel mapPanel = new JPanel();
        mapPanel.setBounds(0,50,800,800);
        mapPanel.setLayout(null);

        JLabel mapLabel = new JLabel();
        mapLabel.setBounds(50,0,800,800);
        ImageIcon mapImage = new ImageIcon(MapMenu.class.getClassLoader().getResource("Temple_Jog_map.png"));
        mapLabel.setIcon(mapImage);
        mapFrame.add(mapLabel);
        mapFrame.setVisible(true);
    }
}
