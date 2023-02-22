package com.game.templejog.gui;

import com.game.templejog.Game;
import com.game.templejog.gui.bottom.BottomSection;
import com.game.templejog.gui.middle.MiddleSection;
import com.game.templejog.gui.top.TopHUD;

public class RunGUI {
    private final Game game;

    public RunGUI(Game game) {
        this.game = game;
    }

    public void runGame() {
        final TopHUD topHUD = new TopHUD(game);
        final MiddleSection middleSection = new MiddleSection(game);
        final BottomSection bottomSection = new BottomSection(game);
        MainContainer mainContainer = new MainContainer(game, topHUD, middleSection, bottomSection);
        mainContainer.setUpMainContainer();

        do {
            MainContainer.getMainContainer().validate();
            MainContainer.getMainContainer().repaint();
        } while (!game.getQuitGame()
                && game.getPlayer().getSteps() < 24
                && game.getPlayer().getHealth() > 0
                && !(game.getCommunicatorOff() && game.getCurrentRoom().getName().equalsIgnoreCase("landing zone")));
        // DONE: create win GUI stuff and call it here
        // game end message, sends you back to title screen
        winAndLoss(mainContainer);
    }

    public void winAndLoss(MainContainer mainContainer) {
        WinAndLossScreen winAndLossScreen = new WinAndLossScreen(game, MainContainer.getMainContainer());
        winAndLossScreen.setUpWinAndLossScreen();
    }

}
