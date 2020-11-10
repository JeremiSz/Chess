package pieces;

import game.Board;

public class Bishop extends Piece{

    public Bishop(boolean team){
        super(team?"♗":"♝","Bishop",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition) {
        int deltaX = firstPosition[0] - secondPosition[0];
        int deltaY = firstPosition[1] - secondPosition[1];

       if((deltaX != deltaY) && (deltaX != deltaY)) return false;

       int moveY = deltaY<0?-1:1;
       if(deltaX<0)
           return checkBetween(secondPosition,firstPosition,moveY);
       else
           return checkBetween(firstPosition,secondPosition,moveY);
    }

    private boolean checkBetween(int[] left, int[] right,int moveY){
       int x = left[0];
       int y = right[0];
        for (int i = left[0]; i < right[0]; i++) {
            x++;
            y += moveY;
            if(Board.hasPiece(x,y))
                return false;
        }
        return true;
    }

}
