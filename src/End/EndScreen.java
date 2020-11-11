package End;

import start.Start;

import javax.swing.*;
import java.awt.*;

public class EndScreen {

    public EndScreen(boolean team,Color colour){

        String output = colour.toString() + " team won\n\nPlay again?";
        int result = JOptionPane.showConfirmDialog(null,output,"Winner!",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);

        switch (result){
            case (JOptionPane.OK_OPTION):
                Start.main(new String[0]);
                break;
            default:
                System.exit(0);
                break;
        }
    }
}
