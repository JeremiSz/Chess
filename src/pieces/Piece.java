package pieces;

import game.Board;

public abstract class Piece {
    private String name,symbol;
    private boolean team;

    public Piece(String symbol,String name,boolean team){
        this.name = name;
        this.team = team;
        this.symbol = symbol;
    }

    public abstract boolean validateMove(int[] firstPosition, int[] secondPosition);
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
}
