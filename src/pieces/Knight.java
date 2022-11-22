package pieces;

import Data.Move;

public class Knight extends Piece{

    public Knight(boolean team){
        super(team?"♘":"♞","Knight",team);
    }

    @Override
    public boolean validateMove(Move move, Piece[][] board) {
        if(isBlocked(move.toX, move.toY, board)) return false;

        int deltaX = move.fromX - move.toX;
        int deltaY = move.fromY - move.toY;

        if(deltaX<0) deltaX = -deltaX;
        if(deltaY<0) deltaY = -deltaY;

        return (deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1);
    }
}