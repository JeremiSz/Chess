package pieces;

public class Queen extends Piece{

    public Queen(boolean team){
        super(team?"♕":"♛","Queen",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition) {
        return false;
    }
}
