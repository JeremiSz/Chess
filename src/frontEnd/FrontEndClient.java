package frontEnd;

import Data.Board;
import Data.Move;
import network.FauxClient;
import network.FauxNetwork;
import network.Message;
import pieces.Piece;

public class FrontEndClient extends FauxClient {
    private final int BACKEND_PORT;
    public static final String RESTART ="RESTART";
    public FrontEndClient(int port,int backend) {
        super(port);
        this.BACKEND_PORT = backend;
    }

    public void setStartingTeam(boolean team){
        Message message = new Message(PORT_ADDRESS,BACKEND_PORT,team);
        FauxNetwork.sendMessage(message);
    }
    public void setBoard(String name){
        Message message = new Message(PORT_ADDRESS,BACKEND_PORT,name);
        FauxNetwork.sendMessage(message);
    }
    public void restart(){
        Message message = new Message(PORT_ADDRESS,PORT_ADDRESS,RESTART);
        FauxNetwork.sendMessage(message);
    }
    public void kill(Piece piece){
        Message message = new Message(PORT_ADDRESS,BACKEND_PORT,piece);
        FauxNetwork.sendMessage(message);
    }
    public void saveBoard(String name){
        Message message = new Message(PORT_ADDRESS,BACKEND_PORT,new Board(name,null));
        FauxNetwork.sendMessage(message);
    }
    public void realMove(Position.Pos from, Position.Pos to){
        Move move = new Move(from.x, from.y, to.x, to.y, false,false);
        Message message = new Message(PORT_ADDRESS,BACKEND_PORT,move);
        FauxNetwork.sendMessage(message);
    }
    public void falseMove(Position.Pos from, Position.Pos to){
        Move move = new Move(from.x, from.y, to.x, to.y, true,false);
        Message message = new Message(PORT_ADDRESS,BACKEND_PORT,move);
        FauxNetwork.sendMessage(message);
    }
}
