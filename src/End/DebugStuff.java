package End;

import game.Board;
import pieces.Piece;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class DebugStuff extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyChar()){

            case ('g'):
                for (Piece[] ps: Board.grid) {
                    for (Piece p: ps) {
                        if(p != null)
                            System.out.print("[" + p.toString() + "]");
                        else
                            System.out.print("[]");
                    }
                    System.out.print("\n");
                }
                break;

            case ('s'):
                try{
                    String output = JOptionPane.showInputDialog(null,"Name board state","Save Board",JOptionPane.QUESTION_MESSAGE);
                    FileOutputStream outputStream = new FileOutputStream(output + ".brd");
                    ObjectOutputStream out = new ObjectOutputStream(outputStream);
                    out.writeObject(Board.grid);
                    //FileWriter out = new FileWriter( output + ".brd");


                    /*for (Piece[] ps: Board.grid) {
                        for (Piece p: ps) {
                            if(p != null)
                                output = '[' + p.toString() + ',' + p.getTeam() + ']';
                            else
                                output = "[null]";
                            out.write(output);
                        }
                    }*/
                    out.close();

                }
                catch (IOException e){
                    e.printStackTrace();
                    System.err.println(e.getMessage() + " " + e.getCause());
                }
        }
    }
}
