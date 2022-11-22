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
    public Piece makePiece(PieceNum num,boolean team){
        Piece piece;
        switch (num){
            case King:
                return new King(team);
            case Pawn:
                return new Pawn(team);
            case Rook:
                return new Rook(team);
            case Queen:
                return  new Queen(team);
            case Bishop:
                return new Bishop(team);
            case Knight:
                return new Knight(team);
            default:
                return null;
        }
    }
}
