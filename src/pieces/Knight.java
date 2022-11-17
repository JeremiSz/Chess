package pieces;

import logic.GameLogic;

public class Knight extends Piece {

    public Knight(boolean team){
        super(team?"♘":"♞","Knight",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition, GameLogic board) {
        if(board.isBlocked(secondPosition,getTeam())) return false;

        int deltaX = firstPosition[0] - secondPosition[0];
        int deltaY = firstPosition[1] - secondPosition[1];

        if(deltaX<0) deltaX = -deltaX;
        if(deltaY<0) deltaY = -deltaY;

        return (deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1);
    }
}