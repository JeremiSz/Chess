package pieces;

import game.Board;

public class Pawn extends Piece{

    public Pawn(boolean team){
        super(team?"♙":"♟","Pawn",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition) {
        int deltaX = firstPosition[0] - secondPosition[0];
        int deltaY = firstPosition[1] - secondPosition[1];

        if(deltaY < 0)deltaY = -deltaY;

        if(deltaX == ((this.getTeam())?1:-1)){
            boolean target = Board.hasPiece(secondPosition[0],secondPosition[1]);
            if(deltaY == 0 && !target) return true;
            else if(deltaY == 1 && target) return true;
        }
        return false;
    }
}
