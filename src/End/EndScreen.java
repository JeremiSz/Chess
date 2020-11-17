package End;

import start.GameControl;

import javax.swing.*;
import java.awt.*;

public class EndScreen {

    public EndScreen(Color colour){

        String output = colour.toString() + " team won\n\nPlay again?";
        int result = JOptionPane.showConfirmDialog(null,output,"Winner!",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);

        switch (result){
            case (JOptionPane.OK_OPTION):
                GameControl.main(new String[0]);
                break;
            default:
                System.exit(0);
                break;
        }
    }
}
