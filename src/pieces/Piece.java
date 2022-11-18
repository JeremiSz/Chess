package pieces;

import backEnd.Backend;
import backEnd.Move;
import game.Board;

import java.io.Serializable;
public abstract class Piece implements Serializable {
    private final String name,symbol;
    private final boolean team;

    public Piece(String symbol,String name,boolean team){
        this.name = name;
        this.team = team;
        this.symbol = symbol;
    }

    public abstract boolean validateMove(Move move, Piece[][] board);

    public String toString(){
        return name;
    }

    public boolean getTeam() {
        return team;
    }

    public String getSymbol() {
        return symbol;
    }

    public void movePiece(int toX, int toY, int fromX, int fromY){
        Board.grid[fromX][fromY] = null;
        Board.grid[toX][toY] = this;
    }

    public void movePiece(int[] to, int[] from){
        Board.grid[from[0]][from[1]] = null;
        Board.grid[to[0]][to[1]] = this;
    }

    protected boolean isBlocked(int x, int y ,Piece[][] board){
        Piece target = board[x][y];
        return (target != null && target.getTeam() == this.team);
    }
    protected static boolean hasPiece(int x,int y, Piece[][] board){
        return !(board[x][y] == null);
    }
}