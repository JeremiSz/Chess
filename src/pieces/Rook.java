package pieces;

import game.Board;

public class Rook extends Piece{

    public Rook(boolean team){
        super(team?"♖":"♜","Rook",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition) {
        if(isBlocked(secondPosition)) return false;

        int deltaX = firstPosition[0] - secondPosition[0];
        int deltaY = firstPosition[1] - secondPosition[1];

        if (deltaX != 0 && deltaY != 0) return false;

        return  !checkbetweenRook(firstPosition, secondPosition, deltaX, deltaY);
    }

    static boolean checkbetweenRook(int[] firstPosition, int[] secondPosition, int deltaX, int deltaY) {
        if (deltaX == 0) {
            if (deltaX < 0) {
                for (int i = firstPosition[1]; i > secondPosition[1]; i--) {
                    if (Board.hasPiece(firstPosition[0], i)) return true;
                }
            } else {
                for (int i = firstPosition[1]; i < secondPosition[1]; i++) {
                    if (Board.hasPiece(firstPosition[0], i)) return true;
                }
            }
        }
        else {
            if (deltaY < 0) {
                for (int i = firstPosition[0]; i > secondPosition[0]; i--) {
                    if (Board.hasPiece(i, firstPosition[1])) return true;
                }
            } else {
                for (int i = firstPosition[0]; i < secondPosition[0]; i++) {
                    if (Board.hasPiece(i, firstPosition[1])) return true;
                }
            }
        }
        return false;
    }
}
