package game;

import pieces.Piece;
import start.Start;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board extends JPanel{

    JFrame window;
    Piece[][] grid = new Piece[8][12];
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
        window.setVisible(true);
    }

    private void makeWindow(){
        Position.setSize();

        window = new JFrame();
        window.add(this);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        this.setPreferredSize(new Dimension(Start.size,Start.size));
        window.pack();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(checkerboard,0,0,null);
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
