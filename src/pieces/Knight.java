package pieces;

public class Knight extends Piece{

    public Knight(boolean team){
        super(team?"♘":"♞","Knight",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition) {
        return false;
    }
}
