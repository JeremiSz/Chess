package pieces;

public class King extends Piece {
    public King(){
        super("when its in");
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition) {
        int deltaX = firstPosition[0] - secondPosition[0];
        int deltaY = firstPosition[1] - secondPosition[1];
        if(deltaX > 1 || deltaX < -1) return false;
        if(deltaY > 1 || deltaY < -1) return false;
        return true;
    }

    @Override
    public String toString() {
        return "King";
    }
}
