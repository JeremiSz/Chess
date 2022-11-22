package Data;

public class Move {
    public int fromX,fromY,toX,toY;
    public boolean isHovering,requestProper;
    public Move(int fromX,int fromY ,int toX, int toY,boolean hover,boolean requestProper){
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.isHovering = hover;
        this.requestProper = requestProper;
    }
}
