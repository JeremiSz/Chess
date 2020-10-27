package pieces;

public abstract class Piece {

    public abstract String getSymbol();
    public abstract boolean validateMove(float firstPositionX,float firstPositionY,float secondPositionX, float secondPositionY);
    public abstract String toString();

}
