package pieces;

import board_manager.BoardSaver;
import microservice.FauxNetwork;
import microservice.FauxServer;

public class MicroservicePiece implements FauxServer {
    private static final int PORT_NUMBER = 65535;
    private PieceFactory factory;

    public MicroservicePiece(PieceFactory factory){
        this.factory = factory;
        FauxNetwork.addToNetwork(PORT_NUMBER,this);
    }
    @Override
    public void receiveMessage(FauxNetwork.Message message) {
        }
    }
}
