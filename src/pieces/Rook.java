package pieces;

public class Rook extends Piece{

    public Rook(boolean team){
        super(team?"♖":"♜","Rook",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition) {
        return false;
    }
}
