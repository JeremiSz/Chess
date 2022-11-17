package logic;

import board_manager.BoardSaver;
import board_manager.BoardSaverFile;
import pieces.Piece;
import pieces.PieceFactory;

import java.io.IOException;

public class GameLogic {
    public Piece[][] grid;
    private Playable[] players;
    boolean currentTeam;

    public GameLogic(String file){
        setTeam(file);
        players = new Playable[2];
        currentTeam = true;
    }
    public boolean hasPiece(int x, int y){
        return !(grid[x][y] == null);
    }

    public void setTeam(String file){
        try {
            BoardSaver boardSaver = new BoardSaverFile();
            grid = (Piece[][]) boardSaver.load(file);
        } catch (IOException | ClassNotFoundException e) {
            grid = new Piece[8][8];
            PieceFactory factory = new PieceFactory();
            for(int y=0; y<grid.length; y++) {
                Piece[] column = grid[y];
                for (int x=0;x<column.length;x++) {
                    Piece piece = factory.createPiece(x,y);
                    if(piece != null)
                        movePiece(x,y,x,y,piece);
                }
            }
        }
    }
    public void movePiece(int toX, int toY, int fromX, int fromY, Piece piece){
        grid[fromX][fromY] = null;
        grid[toX][toY] = piece;
    }
    public void movePiece(int[] to, int[] from,Piece piece){
        grid[from[0]][from[1]] = null;
        grid[to[0]][to[1]] = piece;
    }
    public boolean isBlocked(int[] last,boolean team){
        Piece target = grid[last[0]][last[1]];
        return (target != null && target.getTeam() == team);
    }

    public boolean checkWin(int[] lastPos) {
        if (!hasPiece(lastPos[0], lastPos[1])) return false;

        Piece target = grid[lastPos[0]][lastPos[1]];
        return target.toString().equals("King");
    }
}
