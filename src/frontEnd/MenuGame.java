package frontEnd;

import backEnd.Backend;
import pieces.Piece;
import pieces.PieceFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuGame extends Menu implements MouseListener, KeyListener, MouseMotionListener {
    private Piece selected;
    private final Piece[][] board;
    private UIGame ui;
    private final JFrame window;
    private Position position;
    private boolean currentTeam;
    private Position.Pos firstPos;

    public MenuGame(FrontEndClient client,JFrame window,Settings settings){
        super(client);
        this.selected = null;
        board = new Piece[Backend.BOARD_MAX_INDEX + 1][Backend.BOARD_MAX_INDEX + 1];
        this.client = client;
        this.window = window;
        makeScreen(settings);
        window.add(ui);
        window.setVisible(true);
        window.addMouseListener(this);
        window.addKeyListener(this);
        window.addMouseMotionListener(this);
        window.pack();
        window.setVisible(true);
        currentTeam = settings.getStartingTeam();
    }
    @Override
    public void clean() {
        window.setVisible(false);
        window.remove(ui);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){

            case ('g'):
                printBoard();
                break;
            case ('s'):
                saveBoard();
                break;
            case('p'):
                kill(PieceFactory.PieceNum.Pawn);
                break;
            case ('r'):
                kill(PieceFactory.PieceNum.Rook);
                break;
            case('q'):
                kill(PieceFactory.PieceNum.Queen);
                break;
            case ('k'):
                kill(PieceFactory.PieceNum.King);
                break;
            case ('B'):
                kill(PieceFactory.PieceNum.Bishop);
                break;
            case('c'):
                showSelected();
                break;
        }
    }

    private void showSelected(){
        System.out.println(selected);
    }
    private void printBoard(){
        for (Piece[] ps: board) {
            for (Piece p: ps) {
                if(p != null)
                    System.out.print("[" + p + "]");
                else
                    System.out.print("[]");
            }
            System.out.print("\n");
        }
    }
    private void kill(PieceFactory.PieceNum type){
        PieceFactory factory = new PieceFactory();
        client.kill(factory.makePiece(type,true));
    }
    private void saveBoard(){
        client.saveBoard(JOptionPane.showInputDialog("Name board"));
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Position.Pos pos = position.gridFromScreen(new Position.Pos(e.getX(), e.getY()));

        if (ui.hasPiece(pos,this.currentTeam)) {
            this.firstPos = pos;
            this.selected = board[pos.x][pos.y];
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Position.Pos lastPos = new Position.Pos(e.getX(),e.getY());
        client.realMove(firstPos,lastPos);
        currentTeam = !currentTeam;
        ui.repaint();

        this.firstPos = null;
        this.selected = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    private void makeScreen(Settings settings){
        String imageURL;
        int size;
        switch (settings.getBoardSize()){
            case(2):
                size = 2000;
                imageURL = "images/boardB.png";
                break;
            case(1):
                size = 1000;
                imageURL = "images/boardM.png";
                break;
            default:
                size = 400;
                imageURL = "images/boardS.png";
                break;
        }
        position = new Position(size,8);
        ui = new UIGame(size,imageURL,settings);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Position.Pos tempPos = position.gridFromScreen(new Position.Pos(e.getX(), e.getY()));
        client.falseMove(firstPos,tempPos);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    private class UIGame extends JPanel{
        private Dimension dimension;
        private BufferedImage checkerboard;
        private Color colour1,colour2;

        public UIGame(int size,String url,Settings settings){
            this.setFont(new Font("SansSerif",Font.PLAIN, size /8));
            dimension = new Dimension(size,size);
            checkerboard = getImage(url);
            this.setPreferredSize(dimension);
            colour1 = settings.team1;
            colour2 = settings.team2;
        }


        public Piece getSelected() {
            return selected;
        }
        private BufferedImage getImage(String url){

            try {
                return ImageIO.read(new File(url));
            }
            catch (IOException e){
                System.err.println("file unable to load");
                return null;
            }
        }
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(checkerboard,0,0,null);
            Piece current;
            boolean currentTeam = true;
            g.setColor(this.colour1);
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    current = board[x][y];
                    if(current != null) {
                        if(current.getTeam() != currentTeam){
                            g.setColor(current.getTeam()? this.colour1: this.colour2);
                            currentTeam = current.getTeam();
                        }
                        g.drawString(board[x][y].getSymbol(), position.screenFromGrid(x), position.screenFromGrid(y + 1));
                    }
                }
            }
        }

        public boolean hasPiece(Position.Pos pos){
            return (board[pos.x][pos.y] != null);
        }
        public boolean hasPiece(Position.Pos pos, boolean team){
            Piece piece = board[pos.x][pos.y];
            return (piece != null)&&piece.getTeam() == team ;
        }
    }
}
