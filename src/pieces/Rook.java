package pieces;

import backEnd.Move;
import game.Board;

public class Rook extends Piece{

    public Rook(boolean team){
        super(team?"♖":"♜","Rook",team);
    }

    @Override
    public boolean validateMove(Move move, Piece[][] board) {
        if(isBlocked(move.toX, move.toY, board)) return false;

        int deltaX = move.fromX - move.toX;
        int deltaY = move.fromY - move.toY;

        if (deltaX != 0 && deltaY != 0) return false;
        return  !checkBetweenRook(move, deltaX, deltaY,board);
    }

    static boolean checkBetweenRook(Move move, int deltaX, int deltaY,Piece[][] board) {
        if (deltaX == 0) {
            if (deltaY > 0) {
                for (int i = move.fromY - 1; i > move.toY; i--) {
                    if (hasPiece(move.fromX, i,board)) return true;
                }
            } else {
                for (int i = move.fromY + 1; i < move.toY; i++) {
                    if (hasPiece(move.fromX, i,board)) return true;
                }
            }
        }
        else {
            if (deltaX > 0) {
                for (int i = move.fromX - 1; i > move.toX; i--) {
                    if (hasPiece(i, move.toY,board)) return true;
                }
            } else {
                for (int i = move.fromX + 1; i < move.toX; i++) {
                    if (hasPiece(i, move.fromY, board)) return true;
                }
            }
        }
        return false;
    }
}