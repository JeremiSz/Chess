package game;

import start.Start;

public class Position {
    private static int grid;

    public static void setSize(){
        grid = (Start.size)/8;
    }

    public static int[] gridFromScreen(int x,int y){
        return new int[]{x/grid,y/grid};
    }
    public static int[] gridFromScreen(int[] position){
        return new int[]{position[0]/grid,position[1/grid]};
    }

    public static int[] screenFromGrid(int x,int y){
        return new int[]{x*grid,y*grid};
    }
    public static int[] screenFromGrid(int[] position){
        return new int[]{position[0]*grid,position[1]*grid};
    }
}
