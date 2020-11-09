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

        if(deltaX < 0) deltaX = -deltaX;
        if(deltaY < 0)deltaY = -deltaY;

        if(deltaX == 1){
            Piece target = Board.grid[secondPosition[0]][secondPosition[1]];
            if(deltaY == 0 && target == null)
                return true;
            else if(deltaY == 1 && target != null)
                return true;
        }
        return false;
    }
}
