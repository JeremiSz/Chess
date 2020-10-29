package pieces;

public class Pawn extends Piece{

    public Pawn(boolean team){
        super(team?"♙":"♟","Pawn",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition) {
        return false;
    }
}
