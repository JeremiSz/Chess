package backEnd;

import Data.Board;
import Data.Move;
import network.FauxNetwork;
import network.FauxServer;
import network.Message;
import pieces.Piece;

public class BackendService extends FauxServer{
    private final int PORT_ADDRESS;
    private final int DATA_ADDRESS;
    private final Backend backend;
    public BackendService(int port, int dataAddress,int frontAddress){
        super(port);
        this.PORT_ADDRESS = port;
        this.DATA_ADDRESS = dataAddress;
        this.backend = new Backend(new BackendClient(PORT_ADDRESS,frontAddress,DATA_ADDRESS));
    }
    @Override
    public void receiveMessage(Message message) {
        Class<?> payloadClass = message.payload.getClass();
        if (payloadClass.equals(Board.class)){
            handleNewBoard((Board) message.payload);
        }else if (payloadClass.equals(String.class)) {
            handleBoardName(message);
        }else if (payloadClass.equals(Boolean.class)) {
            handleSetStartingTeam((Boolean) message.payload);
        }else if (payloadClass.equals(Move.class)){
            handleMove((Move) message.payload);
        }else if (payloadClass.equals(Piece.class)) {
            handlePieceKill((Piece) message.payload);
        }

    }
    private void handleNewBoard(Board board){
        if (board.name.isEmpty())
            backend.setBoard((Piece[][]) board.state);
        else{
            backend.saveBoard(board.name);
        }
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
        backend.start();
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
    private void handlePieceKill(Piece piece){
        backend.kill(piece.toString());
    }

}
