package com.game.templejog.gui.middle;

import com.game.templejog.Game;
import com.game.templejog.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MiddleSection {
    private Game game;
    private JPanel currentLocationImage;

    public MiddleSection(Game game) {
        this.game = game;
    }

    public JPanel setUpMiddleSectionJPanel() {
        this.currentLocationImage = new JPanel();
        final String currentLocationImage = this.game.getCurrentRoom().getImage();
        final ImageIcon currentLocationBackgroundIcon =
                new ImageIcon(Objects.requireNonNull(GUI.class.getClassLoader().getResource(currentLocationImage)));

        final JLabel imageLabel = new JLabel(currentLocationBackgroundIcon);
        imageLabel.setBounds(50,0,700,350);

        this.currentLocationImage.add(imageLabel, BorderLayout.CENTER);

        // middle panel w/ image of location
        this.currentLocationImage.add(imageLabel);
        return this.currentLocationImage;
    }
}
