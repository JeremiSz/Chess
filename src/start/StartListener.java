package start;

import game.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartListener implements ActionListener{
    private boolean targetTeam;
    private StartMenu menu;
    private JFrame window;
    private ColourPicker picker;
    private String boardFile;

    public StartListener(JFrame window, StartMenu menu){
            this.menu = menu;
            this.window = window;
            this.picker = new ColourPicker(this);

            this.boardFile = "start.brd";
    }

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
        window.setVisible(false);
        this.targetTeam = team;

        window.remove(menu);
        window.add(picker);
        window.pack();

        window.setVisible(true);
    }

    private void setColour(){
        window.setVisible(false);
        Color colour = picker.getColour();
        menu.updateColour(colour,targetTeam);

        window.remove(picker);
        window.add(menu);
        window.pack();

        window.setVisible(true);
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
                boardFile = picker.getSelectedFile().getName();
    }

    private void StartGame(){
        window.setVisible(false);
        window.remove(menu);
        new Board(boardFile,menu.getTeamColour(true),menu.getTeamColour(false),menu.getBoardSize(),window);
    }
}
