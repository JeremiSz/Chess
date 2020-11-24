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
        System.out.println("first: " + firstPosition[0] + " " + firstPosition[1] + " Second: " + secondPosition[0] + " " + secondPosition[1]);
        System.out.println("DX: " + deltaX + " DY: " + deltaY);
        return  !checkBetweenRook(firstPosition, secondPosition, deltaX, deltaY);
    }

    static boolean checkBetweenRook(int[] firstPosition, int[] secondPosition, int deltaX, int deltaY) {
        if (deltaX == 0) {
            if (deltaY > 0) {
                System.out.println("A");
                for (int i = firstPosition[1] - 1; i > secondPosition[1]; i--) {
                    showSpot(firstPosition[0],i);
                    if (Board.hasPiece(firstPosition[0], i)) return true;
                }
            } else {
                System.out.println("B");
                for (int i = firstPosition[1] + 1; i < secondPosition[1]; i++) {
                    showSpot(firstPosition[0],i);
                    if (Board.hasPiece(firstPosition[0], i)) return true;
                }
            }
        }
        else {
            if (deltaX > 0) {
                System.out.println("C");
                for (int i = firstPosition[0] - 1; i > secondPosition[0]; i--) {
                    showSpot(firstPosition[0],i);
                    if (Board.hasPiece(i, firstPosition[1])) return true;
                }
            } else {
                System.out.println("D");
                for (int i = firstPosition[0] + 1; i < secondPosition[0]; i++) {
                    showSpot(firstPosition[0],i);
                    if (Board.hasPiece(i, firstPosition[1])) return true;
                }
            }
        }
        return false;
    }

    private static void showSpot(int x,int y){
        System.out.println("X: " + x + " Y: " + y + " " + Board.hasPiece(x,y));
    }
}