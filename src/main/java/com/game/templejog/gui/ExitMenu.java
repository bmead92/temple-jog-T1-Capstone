package com.game.templejog.gui;

import javax.swing.*;
import java.awt.*;

public class ExitMenu {
static JFrame frame;
    public static void setUpExitOptions(GUI gui){

        //click event will return an int and we can use that int in the if statement to take action
        int n = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to exit?",
                "Exit Question",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if(n == 0){
            gui.getMainContainer().dispose();
        }
    }
}
