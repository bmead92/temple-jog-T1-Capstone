package com.game.templejog.gui;

import com.game.templejog.Game;
import com.game.templejog.Temple;
import com.game.templejog.client.FileLoader;
import com.game.templejog.gui.bottom.BottomSection;
import com.game.templejog.gui.middle.MiddleSection;
import com.game.templejog.gui.top.TopHUD;

import javax.swing.*;
import java.io.IOException;

public class GUIMain {

    private Game game;

    public GUIMain(Game game){
        this.game = game;
    }


    //gameloop method call on start button action
    public JFrame gameLoop(){

        JFrame frame;

        final TopHUD topHUD = new TopHUD(game);
        final MiddleSection middleSection = new MiddleSection(game);
        final BottomSection bottomSection = new BottomSection(game);
        final MainContainer mainContainer = new MainContainer(game, topHUD, middleSection, bottomSection);

        mainContainer.setUpMainContainer();
        do {
            mainContainer.getMainContainer().validate();
            mainContainer.getMainContainer().repaint();
            frame = mainContainer.getMainContainer();
            return frame;
        } while (!game.getQuitGame()
                && game.getPlayer().getSteps() < 24
                && game.getPlayer().getHealth() > 0
                && !(game.getCommunicatorOff() && game.getCurrentRoom().getName().equalsIgnoreCase("landing zone")));
        // TODO: create win GUI stuff and call it here
        // game end message, sends you back to title screen
//winAndLoss(mainContainer);


    }

    public void winAndLoss(MainContainer mainContainer) {

        WinAndLossScreen winAndLossScreen = new WinAndLossScreen(game, mainContainer.getMainContainer());
       winAndLossScreen.setUpWinAndLossScreen();
    }

}
