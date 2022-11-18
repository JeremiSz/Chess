package pieces;

import backEnd.Move;

public class Queen extends Piece{

    public Queen(boolean team){
        super(team?"♕":"♛","Queen",team);
    }

    @Override
    public boolean validateMove(Move move, Piece[][] board) {
        if(isBlocked(move.toX, move.toY, board)) return false;

        int deltaX = move.fromX - move.toX;
        int deltaY = move.fromY - move.toY;

        if((deltaX == deltaY)||(deltaX == -deltaY)){
            byte directionY;

            if(deltaX<0) {
                directionY = (byte) (deltaY > 0 ? -1 : 1);
                return Bishop.checkBetween(move.fromX,move.fromY, move.toX, directionY,board);
            }
            else {
                directionY = (byte) (deltaY > 0 ? 1 : -1);
                return Bishop.checkBetween(move.toX,move.toY, move.fromX, directionY,board);
            }
        }
        else if(deltaX == 0 || deltaY == 0){
            return  !Rook.checkBetweenRook(move, deltaX, deltaY,board);
        }
        else
            return false;
    }
}