package game;

import pieces.*;
import start.Start;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board extends JPanel{

    JFrame window;
    public static Piece[][] grid = new Piece[8][12];
    BufferedImage checkerboard;


    public Board(){
        String url;
        switch (Start.size){
            case(2000):
                url = "images/boardB.png";
                break;
            case(1000):
                url = "images/boardM.png";
                break;
            default:
                url = "images/boardS.png";
                break;
        }
        checkerboard = getImage(url);

        makeWindow();
        setTeam(false);
        setTeam(true);
        window.pack();
        window.setVisible(true);
    }

    private void makeWindow(){
        Position.setSize();

        window = new JFrame();
        window.add(this);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        this.setPreferredSize(new Dimension(Start.size,Start.size));
        //window.pack();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(checkerboard,0,0,null);
        super.paint(g);
    }

    private void setTeam(boolean team){
        new Rook(team).movePiece(0,team?0:7,0,3);
        new Knight(team).movePiece(1,team?0:7,0,3);
        new Bishop(team).movePiece(2,team?0:7,0,3);
        new King(team).movePiece(3,team?0:7,0,3);
        new Queen(team).movePiece(4,team?0:7,0,3);
        new Bishop(team).movePiece(5,team?0:7,0,3);
        new Knight(team).movePiece(6,team?0:7,0,3);
        new Rook(team).movePiece(7,team?0:7,0,3);

        for (int i = 0; i < 8; i++) {
            this.add(grid[i][team?0:7].getLabel());
            new Pawn(team).movePiece(i,team?1:6,0,3);
            this.add(grid[i][team?1:6].getLabel());
        }


    }

    public static BufferedImage getImage(String url){

        try {
            return ImageIO.read(new File(url));
        }
        catch (IOException e){
            System.err.println("file unable to load");
            return null;
        }
    }
}
