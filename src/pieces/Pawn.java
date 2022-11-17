package pieces;

import logic.GameLogic;

public class Pawn extends Piece {

    public Pawn(boolean team){
        super(team?"♙":"♟","Pawn",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition,GameLogic board) {
        if(board.isBlocked(secondPosition,getTeam())) return false;

        int deltaX = firstPosition[0] - secondPosition[0];
        int deltaY = firstPosition[1] - secondPosition[1];

        if(deltaX < 0)deltaX = -deltaX;

        if(deltaY == ((this.getTeam())?-1:1)){
            boolean target = board.hasPiece(secondPosition[0],secondPosition[1]);
            if(deltaX == 0 && !target) return true;
            else return deltaX == 1 && target;
        }
        return false;
    }
}