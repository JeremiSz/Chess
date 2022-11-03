package debug;

import game.Board;
import game.Player;
import pieces.Piece;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class keyShortcuts extends KeyAdapter {

    Player player;
    public keyShortcuts(Player player){
        this.player = player;
    }
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyChar()){

            case ('g'):
                printBoard();
                break;

            case ('s'):
                saveBoard();
                break;

            case('p'):
                kill("Pawn");
                break;
            case ('r'):
                kill("Rook");
                break;
            case('q'):
                kill("Queen");
                break;
            case ('k'):
                kill("King");
                break;
            case ('b'):
                kill("Bishop");
                break;
            case('c'):
                showSelected();
                break;
        }
    }
    private void showSelected(){
        System.out.print(player.getSelected());
    }

    private void printBoard(){
        for (Piece[] ps: Board.grid) {
            for (Piece p: ps) {
                if(p != null)
                    System.out.print("[" + p + "]");
                else
                    System.out.print("[]");
            }
            System.out.print("\n");
        }
    }

    private void saveBoard(){
        String output = JOptionPane.showInputDialog(null,"Name board state","Save Board",JOptionPane.QUESTION_MESSAGE);
        try{
            FileOutputStream outputStream = new FileOutputStream(output + ".brd");
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(Board.grid);
            out.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.err.println(e.getMessage() + " " + e.getCause());
        }
    }

    private void kill(String name){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(Board.grid[i][j] != null && Board.grid[i][j].toString().equals(name))
                    Board.grid[i][j] = null;
            }

        }
    }
}
