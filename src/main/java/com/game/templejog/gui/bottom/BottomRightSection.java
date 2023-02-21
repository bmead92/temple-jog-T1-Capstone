package com.game.templejog.gui.bottom;

import javax.swing.*;

public class BottomRightSection {
    private final JPanel bottomRightSectionJPanel;

    public BottomRightSection() {
        this.bottomRightSectionJPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(bottomRightSectionJPanel, BoxLayout.Y_AXIS);
        bottomRightSectionJPanel.setLayout(boxLayout);
    }

    public JPanel getBottomRightSectionJPanel() {
        return bottomRightSectionJPanel;
    }
}
