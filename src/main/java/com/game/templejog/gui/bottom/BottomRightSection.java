package com.game.templejog.gui.bottom;

import javax.swing.*;

public class BottomRightSection {
    private JPanel bottomRightSectionJPanel;

    public BottomRightSection() {
        this.bottomRightSectionJPanel = new JPanel();
    }

    public JPanel setUpBottomRightSectionJPanel() {
        return this.bottomRightSectionJPanel;
    }

    public JPanel getBottomRightSectionJPanel() {
        return bottomRightSectionJPanel;
    }

    public void setBottomRightSectionJPanel(JPanel bottomRightSectionJPanel) {
        this.bottomRightSectionJPanel = bottomRightSectionJPanel;
    }
}
