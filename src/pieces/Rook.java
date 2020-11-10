package pieces;

import game.Board;

public class Rook extends Piece{

    public Rook(boolean team){
        super(team?"♖":"♜","Rook",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition) {
        int deltaX = firstPosition[0] - secondPosition[0];
        int deltaY = firstPosition[1] - secondPosition[1];

        if (deltaX != 0 && deltaY != 0) return false;


        if (deltaX == 0) {
            if (deltaX < 0) {
                for (int i = firstPosition[1]; i > secondPosition[1]; i--) {
                    if (Board.hasPiece(firstPosition[0], i)) return false;
                }
            } else {
                for (int i = firstPosition[1]; i < secondPosition[1]; i++) {
                    if (Board.hasPiece(firstPosition[0], i)) return false;
                }
            }
        }
        else {
            if (deltaY < 0) {
                for (int i = firstPosition[0]; i > secondPosition[0]; i--) {
                    if (Board.hasPiece(i, firstPosition[1])) return false;
                }
            } else {
                for (int i = firstPosition[0]; i < secondPosition[0]; i++) {
                    if (Board.hasPiece(i, firstPosition[1])) return false;
                }
            }
        }
        return true;
    }
}
