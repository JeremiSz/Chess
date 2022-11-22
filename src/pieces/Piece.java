package pieces;

import Data.Move;

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

    protected boolean isBlocked(int x, int y ,Piece[][] board){
        Piece target = board[x][y];
        return (target != null && target.getTeam() == this.team);
    }
    protected static boolean hasPiece(int x,int y, Piece[][] board){
        return !(board[x][y] == null);
    }
}