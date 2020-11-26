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
            byte directionY;

            if(deltaX<0) {
                directionY = (byte) (deltaY > 0 ? -1 : 1);
                return Bishop.checkBetween(firstPosition, secondPosition, directionY);
            }
            else {
                directionY = (byte) (deltaY > 0 ? 1 : -1);
                return Bishop.checkBetween(secondPosition, firstPosition, directionY);
            }
        }
        else if(deltaX == 0 || deltaY == 0){
            return  Rook.checkBetweenRook(firstPosition, secondPosition, deltaX, deltaY);
        }
        else
            return false;
    }
}