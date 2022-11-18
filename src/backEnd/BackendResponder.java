package backEnd;

import dataAccess.Board;
import network.FauxClient;
import network.FauxNetwork;
import network.Message;
import pieces.Piece;

public class BackendResponder extends FauxClient {
    private final int FRONT_END_PORT;
    private final String START_MESSAGE = "START", WIN_MESSAGE = "WIN";
    public BackendResponder(int port, int frontendPort) {
        super(port);
        FRONT_END_PORT = frontendPort;
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
    public void winGame(){
        Message message = new Message(PORT_ADDRESS,FRONT_END_PORT,WIN_MESSAGE);
        FauxNetwork.sendMessage(message);
    }
}
