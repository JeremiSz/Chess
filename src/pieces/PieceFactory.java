package pieces;

public class PieceFactory {
    private final int UPPER_CENTER = 4;
    public Piece createPiece(int x, int y){
        if (y > 1 && y < 6)
            return null;
        else if(y == 1 || y == 6)
            return new Pawn(y<UPPER_CENTER);
        else{
            switch (x){
                case 0:
                case 7:
                    return new Rook(y<UPPER_CENTER);
                case 1:
                case 6:
                    return new Knight(y<UPPER_CENTER);
                case 2:
                case 5:
                    return new Bishop(y<UPPER_CENTER);
                case 3:
                    return new King(y<UPPER_CENTER);
                default:
                    return new Queen(y<UPPER_CENTER);

            }
        }
    }
}
