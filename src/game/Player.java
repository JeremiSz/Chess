package game;

import pieces.Piece;
import start.Start;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Player extends MouseAdapter{
    private boolean currentTeam = false;
    private int[] firstPos,lastPos;
    private Piece selected;

    @Override
    public void mousePressed(MouseEvent e) {
        if(this.firstPos != null) return;

        int[] firstPos = Position.gridFromScreen(e.getX(),e.getY());

        if(Board.hasPiece(firstPos[0],firstPos[1]) && Board.grid[firstPos[0]][firstPos[1]].getTeam() == this.currentTeam){
            System.out.print("Got one");
            this.firstPos = firstPos;
            this.selected = Board.grid[firstPos[0]][firstPos[1]];
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(this.lastPos == null || this.firstPos == null) return;
        System.out.print(preMove(lastPos));
        switch (preMove(lastPos)) {
            case (0):
                Start.win(currentTeam);
                break;
            case (1):
                selected.movePiece(lastPos, firstPos);
                currentTeam = !currentTeam;
                Start.render();
                break;
            default:
                break;
        }
        Board.unsetTempPiece();
        this.firstPos = null;
        this.selected = null;
        Start.render();

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int[] tempPos = Position.gridFromScreen(e.getX(),e.getY());
        if(selected != null && selected.validateMove(firstPos,tempPos)) {
            lastPos = tempPos;
            Color team = currentTeam ? Start.team1 : Start.team2;

            int x = Position.screenFromGrid(lastPos[0]);
            int y = Position.screenFromGrid(lastPos[1] + 1);
            Board.setTempPiece(selected.getSymbol(),team ,x ,y);
            Start.render();
        }
    }

    private int preMove(int[] lastPos){
        if(!Board.hasPiece(lastPos[0],lastPos[1])) return 1;

        Piece target = Board.grid[lastPos[0]][lastPos[1]];
        if(target.getTeam() == currentTeam) return 255;
        if(target.toString().equals("King")) return 0;
        else return 1;
    }

    //debug to remove
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.print(e.getX() + " " + e.getY() + "\n");
        int[] output = Position.gridFromScreen(e.getX(),e.getY());
        System.out.print(output[0] + " " + output[1] + "\n");
        if(Board.hasPiece(output[0],output[1])){
            System.out.println(Board.grid[output[0]][output[1]].toString());
        }
    }
}
