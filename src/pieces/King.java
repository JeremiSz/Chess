package pieces;

import backEnd.Move;

public class King extends Piece {

    public King(boolean team){
        super(team?"♔":"♚","King",team);
    }

    @Override
    public boolean validateMove(Move move, Piece[][] board) {
        if(isBlocked(move.toX, move.toY, board)) return false;

        int deltaX = move.fromX - move.toX;
        int deltaY = move.fromY - move.toY;

        if(deltaX > 1 || deltaX < -1) return false;
        return deltaY <= 1 && deltaY >= -1;
    }
}