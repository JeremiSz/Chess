package desktop;

import game.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start implements ActionListener {
    StartMenu menu;
    private boolean targetTeam;
    private String boardFile;

    public Start(){
        menu = new StartMenu(this);
    }

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
            case("SetBoard"):
                setBoard();
                break;
            default:
                System.err.println("Unset Button pressed");
                break;
        }
    }
    private void setTeam(boolean team){
        this.targetTeam = team;
        menu.setColourPicker();
    }

    private void setColour(){
        menu.updateColour(targetTeam);
        menu.setMenu();
    }

    private void setBoard(){
        boardFile = JOptionPane.showInputDialog(
                null,
                "Name board state",
                "Save Board",
                JOptionPane.QUESTION_MESSAGE);
    }

    private void StartGame(){
        new Board(boardFile,menu.team1Colour,menu.team2Colour,window,menu.getBoardSize(),menu.getTeamStart());

    }
}
