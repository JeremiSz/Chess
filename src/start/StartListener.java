package start;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartListener implements ActionListener{
    private boolean targetTeam;

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case("SetTeam1"):
                setTeam(false);
                break;
            case ("SetTeam2"):
                setTeam(true);
                break;
            case("SetColour"):
                setColour();
                break;
        }
    }

    private void setTeam(boolean team){
        Start.startMenu.setVisible(false);
        targetTeam = team;
        Start.colourPicker.getColourWindow().setVisible(true);
    }
    private void setColour(){
        Start.colourPicker.getColourWindow().setVisible(false);
        Color colour = Start.colourPicker.getPicker().getColor();
        if(targetTeam)
            Start.team1 = colour;
        else
            Start.team2 = colour;
        Start.startMenu.setVisible(true);
    }
}
