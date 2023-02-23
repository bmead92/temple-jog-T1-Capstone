package com.game.templejog.gui.bottom;

import com.game.templejog.Game;

import javax.swing.*;
import java.awt.*;

public class BottomSection {
    private Game game;
    private BottomLeftOptions bottomLeftOptions;
    private BottomRightSection bottomRightSection;
    public BottomSection(Game game) {
        this.game = game;
    }

    public JPanel setUpBottomSectionJPanel() {
        JPanel bottomSection = new JPanel();
        bottomSection.setBackground(new Color(5, 23, 38));
        this.bottomLeftOptions = new BottomLeftOptions(game);
        bottomSection.setLayout(new BorderLayout(350, 0));
        bottomSection.add(bottomLeftOptions.setUpBottomLeftOptionsJPanel(), BorderLayout.LINE_START);
        this.bottomRightSection = new BottomRightSection();
        bottomSection.add(bottomRightSection.getBottomRightSectionJPanel(), BorderLayout.CENTER);
        return bottomSection;
    }

    public BottomLeftOptions getBottomLeftOptions() {
        return bottomLeftOptions;
    }

    public void setBottomLeftOptions(BottomLeftOptions bottomLeftOptions) {
        this.bottomLeftOptions = bottomLeftOptions;
    }

    public BottomRightSection getBottomRightSection() {
        return bottomRightSection;
    }

    public void setBottomRightSection(BottomRightSection bottomRightSection) {
        this.bottomRightSection = bottomRightSection;
    }
}

