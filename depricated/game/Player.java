package game;

import pieces.Piece;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Player extends MouseAdapter {

    private final Board board;

    private boolean currentTeam;
    private int[] firstPos, lastPos;
    private Piece selected;

    public Player(Board board, boolean team) {
        currentTeam = team;
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //if (this.firstPos != null) return;

        int[] firstPos = Position.gridFromScreen(e.getX(), e.getY());

        if (Board.hasPiece(firstPos[0], firstPos[1]) && Board.grid[firstPos[0]][firstPos[1]].getTeam() == this.currentTeam) {
            this.firstPos = firstPos;
            this.selected = Board.grid[firstPos[0]][firstPos[1]];
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.lastPos == null || this.firstPos == null) return;

        if (checkWin(lastPos))
            this.board.win(currentTeam);

        else {
            selected.movePiece(lastPos, firstPos);
            currentTeam = !currentTeam;
            board.repaint();
        }

        this.board.unsetTempPiece();

        this.firstPos = null;
        this.selected = null;
        this.lastPos = null;
        
        this.board.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int[] tempPos = Position.gridFromScreen(e.getX(), e.getY());

        if (selected != null && selected.validateMove(firstPos, tempPos)) {
            lastPos = tempPos;

            Color team = currentTeam ? this.board.getColour1() : this.board.getColour2();

            int x = Position.screenFromGrid(lastPos[0]);
            int y = Position.screenFromGrid((lastPos[1] + 1));
            this.board.setTempPiece(selected.getSymbol(), team, x, y);
            this.board.repaint();
        }
    }
    public Piece getSelected() {
        return selected;
    }
}