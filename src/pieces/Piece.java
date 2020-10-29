package pieces;

import game.Board;
import game.Position;
import start.Start;

import javax.swing.*;
import java.awt.*;

public abstract class Piece {
    private String name;
    private boolean team;
    private JLabel label;

    public Piece(String symbol,String name,boolean team){
        this.label = new JLabel(symbol);
        this.name = name;
        this.team = team;
        label.setForeground(team? Start.team1:Start.team2);
        label.setFont(Font.getFont(Font.SERIF));
    }

    public abstract boolean validateMove(int[] firstPosition, int[] secondPosition);
    public String toString(){
        return name;
    }

    public boolean getTeam() {
        return team;
    }

    public JLabel getLabel() {
        return label;
    }

    public void movePiece(int toX,int toY,int fromX,int fromY){
        int[] pos = Position.screenFromGrid(toX,toY);
        label.setAlignmentX(pos[0]);
        label.setAlignmentY(pos[1]);
        Board.grid[fromX][fromY] = null;
        Board.grid[toX][toY] = this;
    }
}
