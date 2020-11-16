package game;

import End.DebugStuff;
import pieces.*;
import start.Start;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Board extends JPanel{

    private JFrame window;
    public static Piece[][] grid;
    private BufferedImage checkerboard;


    public Board(String file){

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
        setTeam(file);
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
        Player mouseAdapt = new Player();

        //debugStuff remove for performance
        DebugStuff ds = new DebugStuff();
        window.addKeyListener(ds);

        this.addMouseListener(mouseAdapt);
        this.addMouseMotionListener(mouseAdapt);
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

        if(tempColour != null){
            g.setColor(tempColour);
            g.drawString(tempSymbol,tempX,tempY);
        }
    }
    //End of refactored code

    private void setTeam(String file){
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream out = new ObjectInputStream(inputStream);
            Board.grid = (Piece[][])out.readObject();
            out.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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
    private static BufferedImage getImage(String url){

        try {
            return ImageIO.read(new File(url));
        }
        catch (IOException e){
            System.err.println("file unable to load");
            return null;
        }
    }
    //End of non-original code

    public static boolean hasPiece(int x,int y){
        return !(grid[x][y] == null);
    }

    private static String tempSymbol;
    private static Color tempColour;
    private static int tempX,tempY;

    public static void setTempPiece(String symbol,Color colour,int x,int y){
        tempSymbol = symbol;
        tempX = x;
        tempY = y;
        tempColour = colour;
    }
    public static void unsetTempPiece(){
        tempColour = null;
    }

    public void cleanUp(){
        window.dispose();
        window = null;
        grid = null;
        checkerboard = null;
        tempColour = null;
    }
}
