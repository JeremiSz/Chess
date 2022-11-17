package logic;

import board_manager.MicroserviceBoardSaver;
import microservice.FauxNetwork;
import microservice.FauxServer;
import pieces.Piece;

public class MicroserviceGameLogic implements FauxServer {
    private static final int PORT_NUMBER = 65533;
    private GameLogic gameLogic;
    public MicroserviceGameLogic(GameLogic gameLogic){
        this.gameLogic = gameLogic;
        FauxNetwork.addToNetwork(PORT_NUMBER,this);
    }
    @Override
    public void receiveMessage(FauxNetwork.Message message) {
        switch (message.source){
            case(MicroserviceBoardSaver.PORT_NUMBER):
                handleBoardReceived(message);
                break;
        }
    }

    private void handleBoardReceived(FauxNetwork.Message message){
        gameLogic.grid = (Piece[][]) message.payload;
    }
}
