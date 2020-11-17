package debug;

import game.Board;
import pieces.Piece;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class keyShortcuts extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyChar()){

            case ('g'):
                printBoard();
                break;

            case ('s'):
                saveBoard();
                break;
        }
    }

    private void printBoard(){
        for (Piece[] ps: Board.grid) {
            for (Piece p: ps) {
                if(p != null)
                    System.out.print("[" + p.toString() + "]");
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
}