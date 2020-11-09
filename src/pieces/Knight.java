package pieces;

public class Knight extends Piece{

    public Knight(boolean team){
        super(team?"♘":"♞","Knight",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition) {
        int deltaX = firstPosition[0] - secondPosition[0];
        int deltaY = firstPosition[1] - secondPosition[1];

        if(deltaX<0) deltaX = -deltaX;
        if(deltaY<0) deltaY = -deltaY;

        if((deltaX == 2 && deltaY ==3 ) || (deltaX == 3 && deltaY ==2))
            return true;
        else
            return false;
    }
}
