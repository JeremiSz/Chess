package start;

import End.EndScreen;
import game.Board;

import java.awt.Color;

public class GameControl {
    public static Color team1;
    public static Color team2;
    static StartMenu startMenu;
    static Board board;
    public static int size;

    public static void main(String[] args) {
        team1 = Color.RED;
        team2 = Color.BLUE;

        startMenu = new StartMenu(team1,team2);
        startMenu.getStartWindow().setVisible(true);
    }


    public static void win(boolean winingTeam){
        board.cleanUp();
        board = null;
        new EndScreen(winingTeam?team1:team2);
    }

    public static void render(){
        board.repaint();
    }
}
