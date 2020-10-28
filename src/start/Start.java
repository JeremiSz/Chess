package start;

import game.Board;

import java.awt.*;

public class Start {
    public static Color team1;
    public static Color team2;
    static StartMenu startMenu;
    static Board board;
    public static int size;

    public static void main(String[] args) {
        team1 = Color.BLACK;
        team2 = Color.WHITE;

        startMenu = new StartMenu(team1,team2);
        startMenu.getStartWindow().setVisible(true);
    }
}
