package game;

public class Position {
    private static int grid;
    private static final int maxGrid = 7,minGrid = 0;

    public static void setSize(int length){
        grid = length/8;
    }

    public static int[] gridFromScreen(int x,int y){
        x = x/grid;
        y = y/grid;

        if(x < minGrid)
            x = minGrid;
        else if(x > maxGrid)
            x = maxGrid;

        if(y < minGrid)
            y = minGrid;
        else if(y > maxGrid)
            y = maxGrid;

        return new int[]{x,y};
    }
    public static int[] gridFromScreen(int[] position){
        for (int i = 0; i < 2; i++) {
            position[i] = position[i]/grid;

            if (position[i] < minGrid)
                position[i] = minGrid;
            else if(position[i] > maxGrid)
                position[i] = maxGrid;
        }

        return position;
    }

    public static int[] screenFromGrid(int x,int y){
        return new int[]{x*grid,y*grid};
    }
    public static int[] screenFromGrid(int[] position){
        return new int[]{position[0]*grid,position[1]*grid};
    }

    public static int gridFromScreen(int xORy){
        xORy /= grid;

        if(xORy < minGrid)
            xORy = minGrid;
        else if(xORy > maxGrid)
            xORy = maxGrid;

        return xORy/grid;
    }

    public static int screenFromGrid(int xORy){
        return xORy*grid;
    }
}