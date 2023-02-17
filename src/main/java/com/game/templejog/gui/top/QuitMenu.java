package com.game.templejog.gui.top;

import javax.swing.*;

public class QuitMenu {
static JFrame frame;
    public static void setUpQuitOptions(){
        //click event will return an int and we can use that int in the if statement to take action
        int n = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to quit?",
                "Quit Message",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if(n == 0){
            System.exit(0);
        }
    }
}
