package frontEnd;

public class Position {
    private final int GRID, MIN_INDEX, MAX_INDEX;
    public Position(int length,int gridCount){
        GRID = length/gridCount;
        MIN_INDEX = 0;
        MAX_INDEX = gridCount - 1;
    }
    public Pos gridFromScreen(Pos pos){
        int x = pos.x;
        int y = pos.y;
        x = x/GRID;
        y = y/GRID;

        if(x < MIN_INDEX)
            x = MAX_INDEX;
        else if(x > MAX_INDEX)
            x = MIN_INDEX;

        if(y < MIN_INDEX)
            y = MIN_INDEX;
        else if(y > MAX_INDEX)
            y = MAX_INDEX;

        pos.x = x;
        pos.y = y;
        return pos;
    }


    public int screenFromGrid(int xORy){
        return xORy*GRID;
    }
    public static class Pos{
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
        public Pos(){
            this(0,0);
        }
        public int x,y;
    }
}
