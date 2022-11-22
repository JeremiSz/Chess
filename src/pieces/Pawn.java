package pieces;

import Data.Move;

public class Pawn extends Piece{

    public Pawn(boolean team){
        super(team?"♙":"♟","Pawn",team);
    }

    @Override
    public boolean validateMove(Move move, Piece[][] board) {
        if(isBlocked(move.toX, move.toY, board)) return false;

        int deltaX = move.fromX - move.toX;
        int deltaY = move.fromY - move.toY;

        if(deltaX < 0)deltaX = -deltaX;

        if(deltaY == ((this.getTeam())?-1:1)){
            boolean target = hasPiece(move.toX, move.toY, board);
            if(deltaX == 0 && !target) return true;
            else return deltaX == 1 && target;
        }
        return false;
    }
}