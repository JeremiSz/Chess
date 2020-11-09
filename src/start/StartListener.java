package start;

import game.Board;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartListener implements ActionListener{
    private boolean targetTeam;

    @Override
    public void actionPerformed(ActionEvent e) {
        /*****************************************************
         *    Title: ActionEvent Class
         *    Author: Oracle
         *    Site owner/sponsor: https://docs.oracle.com/
         *    Date: 2020
         *    Code version: edited 20 August 2020
         *    Availability: https://docs.oracle.com/javase/7/docs/api/java/awt/event/ActionEvent.html (Accessed 9 November 2020)
         *    Modified:  used getActionCommand() found via Intellj
         *****************************************************/
        switch (e.getActionCommand()){
            //end of non-original code
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
        this.targetTeam = team;
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
