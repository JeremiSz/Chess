package start;

import game.Board;

import javax.swing.*;
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
            case("SetBoard"):
                setBoard();
                break;
            default:
                System.err.println("Unset Button pressed");
                break;
        }
    }

    private void setTeam(boolean team){
        GameControl.startMenu.getStartWindow().setVisible(false);
        this.targetTeam = team;
        GameControl.startMenu.getColourPicker().getColourWindow().setVisible(true);
    }
    private void setColour(){
        GameControl.startMenu.getColourPicker().getColourWindow().setVisible(false);
        Color colour = GameControl.startMenu.getColourPicker().getPicker().getColor();
        GameControl.startMenu.updateColour(colour,targetTeam);
        GameControl.startMenu.getStartWindow().setVisible(true);
    }

    private void setBoard(){
        JFrame window;
        JFileChooser picker;
            window = new JFrame();
            picker = new JFileChooser();
            window.add(picker);
            window.pack();

            int status = picker.showOpenDialog(null);
            if(status == JFileChooser.APPROVE_OPTION)
                StartMenu.boardFile = picker.getSelectedFile().getName();
    }

    private void StartGame(){
        switch (GameControl.startMenu.getSize()){
            case(0):
                GameControl.size = 400;
                break;
            case(1):
                GameControl.size = 1000;
                break;
            case(2):
                GameControl.size = 2000;
                break;
            default:
                GameControl.size = 400;
                System.err.println("invalid size");
                break;
        }
        GameControl.board = new Board(StartMenu.getBoardFile());
        GameControl.startMenu.cleanUp();
    }
}
