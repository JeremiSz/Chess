package backEnd;

import Data.Board;
import network.FauxClient;
import network.FauxNetwork;
import network.Message;
import pieces.Piece;

public class BackendClient extends FauxClient {
    private final int FRONT_END_PORT,DATA_ACCESS_PORT;
    public static final String START_MESSAGE = "START", WIN_MESSAGE = "WIN";
    public BackendClient(int port, int frontendPort,int dataAccessPort) {
        super(port);
        FRONT_END_PORT = frontendPort;
        DATA_ACCESS_PORT = dataAccessPort;
    }

    public void updateScreen(Piece[][] state){
        Board board = new Board(null,state);
        Message message = new Message(PORT_ADDRESS,FRONT_END_PORT,board);
        FauxNetwork.sendMessage(message);
    }
    public void startGame(){
        Message message = new Message(PORT_ADDRESS,FRONT_END_PORT,START_MESSAGE);
        FauxNetwork.sendMessage(message);
    }
    public void winGame(boolean winningTeam){
        int encodedWinningTeam = winningTeam?1:0;
        encodedWinningTeam = encodedWinningTeam << 17;
        int port  = PORT_ADDRESS + encodedWinningTeam;
        Message message = new Message(port,FRONT_END_PORT,WIN_MESSAGE);
        FauxNetwork.sendMessage(message);
    }
    public void saveBoard(String name,Piece[][] state){
        Board board = new Board(name,state);
        Message message = new Message(PORT_ADDRESS,DATA_ACCESS_PORT,board);
        FauxNetwork.sendMessage(message);
    }
}
