package debug;

import game.Position;
import start.Start;

public class testingPosition {

    public static void main(String[] args) {
        for (int i = 0; i < 400; i += 10) {
            testPosition(i,i);
        }
        System.exit(0);
    }
    private static void testPosition(int screenX, int screenY){
        int gridX,gridY;
        Start.size = 400;
        Position.setSize();

        gridX = Position.gridFromScreen(screenX);
        gridY = Position.gridFromScreen(screenY);
        String output = "Screen in: " + screenX + " " + screenY + " Grid: " + gridX + " " +gridY + " Screen out: " +
                Position.screenFromGrid(gridX) + " " + Position.screenFromGrid(gridY);
        System.out.println(output);
    }
}
