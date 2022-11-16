package game;

import debug.keyShortcuts;
import microservice.board_manager.BoardSaver;
import microservice.board_manager.BoardSaverFile;
import pieces.*;
import start.GameControl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Board extends JPanel{

    private final JFrame window;
    public static Piece[][] grid;
    private BufferedImage checkerboard;

    private final Color team1;
    private final Color team2;

    public Board(String file,Color team1, Color team2, int size,JFrame window, boolean selectedTeam){
        super();
        this.team1 = team1;
        this.team2 = team2;
        this.window = window;

        makeScreen(size,selectedTeam);
        setTeam(file);
        window.pack();
        window.setVisible(true);

    }

    private void makeScreen(int size,boolean team){

        String url;
        Dimension boardSize;
        int smallSize = 400;
        int mediumSize = 1000;
        int largeSize = 2000;
        switch (size){

            case(2):
                url = "images/boardB.png";
                Position.setSize(largeSize);
                this.setFont(new Font("SansSerif",Font.PLAIN, largeSize /8));
                boardSize = new Dimension(largeSize, largeSize);
                break;

            case(1):
                url = "images/boardM.png";
                Position.setSize(mediumSize);
                this.setFont(new Font("SansSerif",Font.PLAIN, mediumSize /8));
                boardSize = new Dimension(mediumSize, mediumSize);
                break;

            case(0):
                Position.setSize(smallSize);
                this.setFont(new Font("SansSerif",Font.PLAIN, smallSize /8));
                boardSize = new Dimension(smallSize, smallSize);
                url = "images/boardS.png";
                break;

            default:
                System.err.println("Invalid Size");
                Position.setSize(smallSize);
                boardSize = new Dimension(smallSize, smallSize);
                url = "images/boardS.png";
                break;
        }
        checkerboard = getImage(url);
        window.add(this);
        Player mouseAdapt = new Player(this,team);

        keyShortcuts keyAdapter = new keyShortcuts(mouseAdapt);
        window.addKeyListener(keyAdapter);

        this.addMouseListener(mouseAdapt);
        this.addMouseMotionListener(mouseAdapt);
        this.setPreferredSize(boardSize);
    }


    /*****************************************************
     *    Title: Class Graphics
     *    Author: Oracle
     *    Site owner/sponsor: https://docs.oracle.com/
     *    Date: 2020
     *    Code version: edited 20 August 2020
     *    Availability: https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html (Accessed 9 November 2020)
     *    Modified:  Added everything but the methods of the Graphics class
     *****************************************************/
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(checkerboard,0,0,null);
        Piece current;
        boolean currentTeam = true;
        g.setColor(this.team1);
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                current = grid[x][y];
                if(current != null) {
                    if(current.getTeam() != currentTeam){
                        g.setColor(current.getTeam()? this.team1: this.team2);
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
            BoardSaver boardSaver = new BoardSaverFile();
            grid = (Piece[][]) boardSaver.load(file);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            grid = new Piece[8][8];
            boolean team = true;
            for (int i = 0; i < 2; i++) {
                new Rook(team).movePiece(0,team?0:7,0,3);
                new Knight(team).movePiece(1,team?0:7,0,3);
                new Bishop(team).movePiece(2,team?0:7,0,3);
                new King(team).movePiece(3,team?0:7,0,3);
                new Queen(team).movePiece(4,team?0:7,0,3);
                new Bishop(team).movePiece(5,team?0:7,0,3);
                new Knight(team).movePiece(6,team?0:7,0,3);
                new Rook(team).movePiece(7,team?0:7,0,3);

                for (int j = 0; j < 8; j++) {
                    new Pawn(team).movePiece(j, team ? 1 : 6, 0, 3);
                }

                team = false;
            }

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

    public void setTempPiece(String symbol,Color colour,int x,int y){
        tempSymbol = symbol;
        tempX = x;
        tempY = y;
        tempColour = colour;
    }
    public void unsetTempPiece(){
        tempColour = null;
    }

    public Color getColour1(){
        return team1;
    }
    public Color getColour2(){
        return team2;
    }

    public void win(boolean winingTeam) {
        window.remove(this);
        String output = "Team" + (winingTeam?1:2) + " team won\n\nPlay again?";
        int result = JOptionPane.showConfirmDialog(null, output, "Winner!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            GameControl.startGame();
        } else {
            System.exit(0);
        }
    }
}