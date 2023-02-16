package com.game.templejog.gui;

import com.game.templejog.Game;
import com.game.templejog.gui.bottom.BottomSection;
import com.game.templejog.gui.middle.MiddleSection;
import com.game.templejog.gui.top.TopHUD;

import javax.swing.*;
import java.awt.*;

public class MainContainer {

    public static final int MAIN_CONTAINER_WIDTH = 800;
    public static final int MAIN_CONTAINER_HEIGHT = 800;
    private Game game;
    private TopHUD topHUD;
    private MiddleSection middleSection;
    private static BottomSection bottomSection;
    private JFrame mainContainer;

    public MainContainer(Game game, TopHUD topHUD, MiddleSection middleSection, BottomSection bottomSection) {
        // main container
        this.mainContainer = new JFrame();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.setBounds(0, 0, MAIN_CONTAINER_WIDTH, MAIN_CONTAINER_HEIGHT);
        this.game = game;
        this.topHUD = topHUD;
        this.middleSection = middleSection;
        MainContainer.bottomSection = bottomSection;
    }

    public void setUpMainContainer() {
        JFrame mainContainer = new JFrame();
        this.mainContainer = mainContainer;
        mainContainer.setLayout(new BorderLayout());
        mainContainer.setBounds(0, 0, MAIN_CONTAINER_WIDTH, MAIN_CONTAINER_HEIGHT);
        mainContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainContainer.add(this.topHUD.setUpTopHUDJPanel(), BorderLayout.PAGE_START);
        this.mainContainer.add(this.middleSection.setUpMiddleSectionJPanel(game.getCurrentRoom()), BorderLayout.CENTER);
        this.mainContainer.add(bottomSection.setUpBottomSectionJPanel(), BorderLayout.PAGE_END);
        mainContainer.pack();
        mainContainer.setVisible(true);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public TopHUD getTopHUD() {
        return topHUD;
    }

    public void setTopHUD(TopHUD topHUD) {
        this.topHUD = topHUD;
    }

    public MiddleSection getMiddleSection() {
        return middleSection;
    }

    public void setMiddleSection(MiddleSection middleSection) {
        this.middleSection = middleSection;
    }

    public static BottomSection getBottomSection() {
        return bottomSection;
    }

    public void setBottomSection(BottomSection bottomSection) {
        MainContainer.bottomSection = bottomSection;
    }

    public JFrame getMainContainer() {
        return mainContainer;
    }

    public void setMainContainer(JFrame mainContainer) {
        this.mainContainer = mainContainer;
    }
}
