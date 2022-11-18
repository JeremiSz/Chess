package pieces;

public class King extends Piece {

    public King(boolean team){
        super(team?"♔":"♚","King",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition) {
        if(isBlocked(secondPosition)) return false;

        int deltaX = firstPosition[0] - secondPosition[0];
        int deltaY = firstPosition[1] - secondPosition[1];

        if(deltaX > 1 || deltaX < -1) return false;
        return deltaY <= 1 && deltaY >= -1;
    }
}