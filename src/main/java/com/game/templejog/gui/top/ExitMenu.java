package com.game.templejog.gui.top;

import javax.swing.*;

public class ExitMenu {
static JFrame frame;
    public static void setUpExitOptions(){
        //click event will return an int and we can use that int in the if statement to take action
        int n = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to exit?",
                "Exit Question",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if(n == 0){
            System.exit(0);
        }
    }
}
