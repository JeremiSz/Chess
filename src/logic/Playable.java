package logic;

public abstract class Playable {
    private GameLogic board;
    private boolean team;
    private int[] firstPos, lastPos;
    private pieces.Piece selected;
    private boolean enabled;
    public Playable(boolean team, GameLogic board){
        this.team = team;
    }
    public void pressed(int x, int y){

    }
    public void release(int x, int y){

    }
    public void hovered(int x,int y){

    }

}
