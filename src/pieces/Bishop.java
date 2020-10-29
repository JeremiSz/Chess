package pieces;

public class Bishop extends Piece{

    public Bishop(boolean team){
        super(team?"♗":"♝","Bishop",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition) {
        return false;
    }
}
