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

        this.setFont(new Font("SansSerif",Font.PLAIN,Start.size/8));
    }

    @Override
    /*****************************************************
     *    Title: Class Graphics
     *    Author: Oracle
     *    Site owner/sponsor: https://docs.oracle.com/
     *    Date: 2020
     *    Code version: edited 20 August 2020
     *    Availability: https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html (Accessed 9 November 2020)
     *    Modified:  Added everything but the methods of the Graphics class
     *****************************************************/
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(checkerboard,0,0,null);
        Piece current;
        boolean currentTeam = true;
        g.setColor(Start.team1);
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                current = grid[x][y];
                if(current != null) {
                    if(current.getTeam() != currentTeam){
                        g.setColor(current.getTeam()?Start.team1:Start.team2);
                        currentTeam = current.getTeam();
                    }
                    g.drawString(grid[x][y].getSymbol(), Position.screenFromGrid(x), Position.screenFromGrid(y + 1));
                }
            }
        }
    }
    //End of refactored code

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
            new Pawn(team).movePiece(i,team?1:6,0,3);
        }
    }
    /*****************************************************
     *    Title: loading image tutorial
     *    Author: Oracle
     *    Site owner/sponsor: https://docs.oracle.com/
     *    Date: 2020
     *    Code version: edited 20 August 2020
     *    Availability: https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html (Accessed 9 November 2020)
     *    Modified:  Added everything but the methods of the Graphics class
     *****************************************************/
    public static BufferedImage getImage(String url){

        try {
            return ImageIO.read(new File(url));
        }
        catch (IOException e){
            System.err.println("file unable to load");
            return null;
        }
    }
    //End of non-original code
}
