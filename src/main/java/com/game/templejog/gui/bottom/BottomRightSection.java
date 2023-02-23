package com.game.templejog.gui.bottom;

import javax.swing.*;
import java.awt.*;

public class BottomRightSection {
    private final JPanel bottomRightSectionJPanel;

    public BottomRightSection() {
        this.bottomRightSectionJPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(bottomRightSectionJPanel, BoxLayout.Y_AXIS);
        bottomRightSectionJPanel.setLayout(boxLayout);
        bottomRightSectionJPanel.setBackground(new Color(129,255,217));

    }

    public JPanel getBottomRightSectionJPanel() {
        return bottomRightSectionJPanel;
    }
}
