package start;

import game.Board;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartListener implements ActionListener{
    private boolean targetTeam;

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case("SetTeam1"):
                setTeam(true);
                break;
            case ("SetTeam2"):
                setTeam(false);
                break;
            case("SetColour"):
                setColour();
                break;
            case("Start"):
                StartGame();
                break;
            default:
                System.err.println("Unset Button pressed");
                break;
        }
    }

    private void setTeam(boolean team){
        Start.startMenu.getStartWindow().setVisible(false);
        targetTeam = team;
        Start.startMenu.getColourPicker().getColourWindow().setVisible(true);
    }
    private void setColour(){
        Start.startMenu.getColourPicker().getColourWindow().setVisible(false);
        Color colour = Start.startMenu.getColourPicker().getPicker().getColor();
        Start.startMenu.updateColour(colour,targetTeam);
        Start.startMenu.getStartWindow().setVisible(true);
    }

    private void StartGame(){
        switch (Start.startMenu.getSize()){
            case(0):
                Start.size = 400;
                break;
            case(1):
                Start.size = 1000;
                break;
            case(2):
                Start.size = 2000;
                break;
            default:
                Start.size = 400;
                System.err.println("invalid size");
                break;
        }
        Start.board = new Board();
        Start.startMenu.cleanUp();
    }
}
