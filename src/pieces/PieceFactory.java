package pieces;

import backEnd.Backend;

public class PieceFactory {
    public enum PieceNum{
        Bishop,
        King,
        Knight,
        Pawn,
        Queen,
        Rook
    }
    public void makePiece(PieceNum num,int x, boolean team,Piece[][] board){
        final int BOARD_START = 0, BOARD_END = 7;
        int y = team? Backend.BOARD_MIN_INDEX :Backend.BOARD_MAX_INDEX ;
        Piece piece;
        switch (num){
            case King:
                piece=  new King(team);
                break;
            case Pawn:
                piece =  new Pawn(team);
                break;
            case Rook:
                piece =  new Rook(team);
                break;
            case Queen:
                piece =  new Queen(team);
                break;
            case Bishop:
                piece =  new Bishop(team);
                break;
            case Knight:
                piece =  new Knight(team);
                break;
            default:
                piece = null;
                break;
        }
        board[x][y] = piece;
    }
}
