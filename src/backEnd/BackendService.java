package backEnd;

import dataAccess.Board;
import network.FauxNetwork;
import network.FauxServer;
import network.Message;
import pieces.Piece;

public class BackendService extends FauxServer{
    private int PORT_ADDRESS;
    private int DATA_ADDRESS;
    private Backend backend;
    public BackendService(int port, int dataAddress,int frontAddress){
        super(port);
        this.PORT_ADDRESS = port;
        this.backend = new Backend(new BackendResponder(PORT_ADDRESS,frontAddress));
        this.DATA_ADDRESS = dataAddress;
    }
    @Override
    public void receiveMessage(Message message) {
        if (message.payload.getClass().equals(Board.class)){
            handleNewBoard((Board) message.payload);
        } else if (message.payload.getClass().equals(String.class)) {
            handleBoardName(message);
        } else if (message.payload.getClass().equals(Boolean.class)) {
            handleSetStartingTeam((Boolean) message.payload);
        }else if (message.payload.getClass().equals(Move.class)){
            handleMove((Move) message.payload);
        }

    }
    private void handleNewBoard(Board board){
        backend.setBoard((Piece[][]) board.state);
    }
    private void handleBoardName(Message message){
        String name = (String) message.payload;
        if (name.isEmpty()){
            backend.setDefaultBoard();
        }
        else {
            message.source = PORT_ADDRESS;
            message.destination = DATA_ADDRESS;
            FauxNetwork.sendMessage(message);
        }
        backend.updateBoardState();
    }
    private void handleSetStartingTeam(Boolean team){
        backend.setCurrentTeam(team);
    }
    private void handleMove(Move move){
        if (move.requestProper){
            backend.requestProper();
        }else if(move.isHovering){
            backend.falseMove(move);
        }else {
            backend.trueMove(move);
        }
    }

}
