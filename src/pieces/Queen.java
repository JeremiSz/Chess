package pieces;

public class Queen extends Piece{

    public Queen(boolean team){
        super(team?"♕":"♛","Queen",team);
    }

    @Override
    public boolean validateMove(int[] firstPosition, int[] secondPosition) {
        if(isBlocked(secondPosition)) return false;

        int deltaX = firstPosition[0] - secondPosition[0];
        int deltaY = firstPosition[1] - secondPosition[1];

        if((deltaX == deltaY)||(deltaX == -deltaY)){
            byte moveY = (byte)(deltaY<0?-1:1);
            if(deltaX<0)
                return Bishop.checkBetween(secondPosition,firstPosition,moveY);
            else
                return Bishop.checkBetween(firstPosition,secondPosition,moveY);
        }
        else if(deltaX == 0 || deltaY == 0){
            return  Rook.checkBetweenRook(firstPosition, secondPosition, deltaX, deltaY);
        }
        else
            return false;
    }
}