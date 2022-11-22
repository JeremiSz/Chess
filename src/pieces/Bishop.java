package pieces;

import Data.Move;

public class Bishop extends Piece{

    public Bishop(boolean team){
        super(team?"♗":"♝","Bishop",team);
    }

    @Override
    public boolean validateMove(Move move, Piece[][] board) {
        if(isBlocked(move.toX, move.toY,board)) return false;

        int deltaX = move.fromX - move.toX;
        int deltaY = move.fromY - move.toY;

       if((deltaX != deltaY) && (deltaX != -deltaY)) return false;

//the function only goes left to right. By reversing the order of the argument locations I can avoid writing another
       if(deltaX<0) {
           byte directionY = (byte)(deltaY>0?-1:1);
           return checkBetween(move.fromX,move.fromY, move.toX, directionY,board);
       }
       else {
           byte directionY = (byte)(deltaY>0?1:-1);
           return checkBetween(move.toX, move.toY, move.fromX, directionY,board);
       }

    }

    static boolean checkBetween(int leftX,int leftY, int rightX,byte moveY,Piece[][] board){
       leftX += 1;
       while (leftX < rightX){
            leftY += moveY;
            if(hasPiece(leftX,leftY,board))  return false;
           leftX++;
        }
        return true;
    }

}
