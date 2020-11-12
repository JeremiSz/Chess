package game;

import start.Start;

public class Position {
    private static int grid;

    public static int getGrid() {
        return grid;
    }

    public static void setSize(){
        grid = (Start.size)/8;
    }

    public static int[] gridFromScreen(int x,int y){
        if(x < 0) x = 0;
        else if(x > Start.size) x = Start.size;

        if(y < 0) y = 0;
        else if(y > Start.size) y = Start.size;

        x = x/grid;
        y = y/grid;

        return new int[]{x,y};
    }
    public static int[] gridFromScreen(int[] position){
        if(position[0] < 0) position[0] = 0;
        else if(position[0] > Start.size) position[0] = Start.size;

        if(position[1] < 0) position[1] = 0;
        else if(position[1] > Start.size) position[1] = Start.size;

        position[0] = position[0]/grid;
        position[1] = position[1]/grid;

        return position;
    }

    public static int[] screenFromGrid(int x,int y){
        return new int[]{x*grid,y*grid};
    }
    public static int[] screenFromGrid(int[] position){
        return new int[]{position[0]*grid,position[1]*grid};
    }

    public static int gridFromScreen(int xORy){
        if(xORy < 0) xORy = 0;
        else if(xORy > Start.size) xORy = Start.size;

        return xORy/grid;
    }

    public static int screenFromGrid(int xORy){
        return xORy*grid;
    }
}
