package pieces;

import logic.GameLogic;

public class Rook extends Piece {

    public Rook(boolean team){
        super(team?"♖":"♜","Rook",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition,GameLogic board) {
        if(board.isBlocked(secondPosition,getTeam())) return false;

        int deltaX = firstPosition[0] - secondPosition[0];
        int deltaY = firstPosition[1] - secondPosition[1];

        if (deltaX != 0 && deltaY != 0) return false;
        return  !checkBetweenRook(firstPosition, secondPosition, deltaX, deltaY,board);
    }

    static boolean checkBetweenRook(int[] firstPosition, int[] secondPosition, int deltaX, int deltaY, GameLogic board) {
        if (deltaX == 0) {
            if (deltaY > 0) {
                System.out.println("A");
                for (int i = firstPosition[1] - 1; i > secondPosition[1]; i--) {
                    showSpot(firstPosition[0],i,board);
                    if (board.hasPiece(firstPosition[0], i)) return true;
                }
            } else {
                System.out.println("B");
                for (int i = firstPosition[1] + 1; i < secondPosition[1]; i++) {
                    showSpot(firstPosition[0],i,board);
                    if (board.hasPiece(firstPosition[0], i)) return true;
                }
            }
        }
        else {
            if (deltaX > 0) {
                System.out.println("C");
                for (int i = firstPosition[0] - 1; i > secondPosition[0]; i--) {
                    showSpot(firstPosition[0],i,board);
                    if (board.hasPiece(i, firstPosition[1])) return true;
                }
            } else {
                System.out.println("D");
                for (int i = firstPosition[0] + 1; i < secondPosition[0]; i++) {
                    showSpot(firstPosition[0],i,board);
                    if (board.hasPiece(i, firstPosition[1])) return true;
                }
            }
        }
        return false;
    }

    private static void showSpot(int x,int y,GameLogic board){
        System.out.println("X: " + x + " Y: " + y + " " + board.hasPiece(x,y));
    }
}