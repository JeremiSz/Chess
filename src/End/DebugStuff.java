package End;

import game.Board;
import pieces.Piece;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DebugStuff extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){
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
        }
    }
}
