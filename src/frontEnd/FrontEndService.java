package frontEnd;

import backEnd.BackendClient;
import Data.Board;
import network.FauxServer;
import network.Message;
import pieces.Piece;

public class FrontEndService extends FauxServer {
    private final FrontEnd frontEnd;
    public FrontEndService(int port,int backend) {
        super(port);
        frontEnd = new FrontEnd(port,backend);
    }

    @Override
    public void receiveMessage(Message message) {
        Class<?> payloadClass = message.payload.getClass();
        if(payloadClass.equals(Board.class)){
            handleNewBoard((Board) message.payload);
        } else if (payloadClass.equals(String.class)){
            handleEvent((String) message.payload,message.source);
        }
    }

    private void handleNewBoard(Board board){
        frontEnd.setBoard((Piece[][]) board.state);
    }
    private void handleEvent(String event,int source){
        switch (event){
            case (BackendClient.START_MESSAGE):
                frontEnd.startGame();
                break;
            case(BackendClient.WIN_MESSAGE):
                int winningTeam = source >> 17;
                frontEnd.win(winningTeam > 0);
                break;
            case(FrontEndClient.RESTART):
                frontEnd.restart();
                break;
            default:
                break;
        }
    }
}
