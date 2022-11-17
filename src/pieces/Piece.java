package pieces;

import game.Board;
import logic.GameLogic;

import java.io.Serializable;

public abstract class Piece implements Serializable {
    private final String name,symbol;
    private final boolean team;
    public Piece(String symbol,String name,boolean team){
        this.name = name;
        this.team = team;
        this.symbol = symbol;
    }
    public abstract boolean validateMove(int[] firstPosition, int[] secondPosition, GameLogic logic);
    public String toString(){
        return name;
    }
    public boolean getTeam() {
        return team;
    }
    public String getSymbol() {
        return symbol;
    }

}