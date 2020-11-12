import game.Position;
import start.Start;

public class testingPosition {

    public static void main(String[] args) {
        int screenX,screenY,gridX,gridY;
        screenX = 100;
        screenY = 100;
        Start.size = 400;
        Position.setSize();

        gridX = Position.gridFromScreen(screenX);
        gridY = Position.gridFromScreen(screenY);
        String output = "Screen in: " + screenX + " " + screenY + "\nGrid: " + gridX + " " +gridY + "\nScreen out: " +
                Position.screenFromGrid(gridX) + " " + Position.screenFromGrid(gridY) + "\n" + Position.getGrid();
        System.out.print(output);
        System.exit(0);
    }

}
