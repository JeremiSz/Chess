package pieces;

import game.Board;

public class Bishop extends Piece {

    public Bishop(boolean team){
        super(team?"♗":"♝","Bishop",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition) {
        if(isBlocked(secondPosition)) return false;

        int deltaX = firstPosition[0] - secondPosition[0];
        int deltaY = firstPosition[1] - secondPosition[1];

       if((deltaX != deltaY) && (deltaX != -deltaY)) return false;

//the function only goes left to right. By reversing the order of the argument locations I can avoid writing another
       if(deltaX<0) {
           byte directionY = (byte)(deltaY>0?-1:1);
           return checkBetween(firstPosition, secondPosition, directionY);
       }
       else {
           byte directionY = (byte)(deltaY>0?1:-1);
           return checkBetween(secondPosition, firstPosition, directionY);
       }

    }

    static boolean checkBetween(int[] left, int[] right,byte moveY){
       int x = left[0] + 1;
       int y = left[1];
       while (x < right[0]){
            y += moveY;
            if(Board.hasPiece(x,y))  return false;
            x++;
        }
        return true;
    }

}
