package frontEnd;

import pieces.Piece;

import javax.swing.*;

public class FrontEnd {
    private Piece[][] board;
    private final JFrame window;
    private Menu currentMenu;
    private final FrontEndClient client;
    private final Settings settings;
    public FrontEnd(int portAddress,int backend){
        window = createWindow();
        client = new FrontEndClient(portAddress,backend);
        settings = new Settings();
        currentMenu = new MenuStart(window,client,settings);
    }
    private JFrame createWindow(){
        JFrame window = new JFrame("Chess");
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return window;
    }

    public void setBoard(Piece[][] board){
        this.board = board;
        currentMenu.dirtyBit();
    }
    public void startGame(){
        currentMenu.clean();
        if (board == null){
            client.setBoard("");
        }
        currentMenu = new MenuGame(client,window,settings,board);
    }
    public void win(boolean winningTeam){
        currentMenu.clean();
        currentMenu = new MenuWin(winningTeam,client);
    }
    public void restart(){
        currentMenu.clean();
        currentMenu = new MenuStart(window,client,settings);
    }
}
