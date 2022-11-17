package pieces;

import microservice.FauxNetwork;
import microservice.FauxServer;

public class MicroservicePiece implements FauxServer {
    private static final int PORT_NUMBER = 65535;
    private static final String COMMAND = "";
    private PieceFactory factory;

    public MicroservicePiece(PieceFactory factory){
        this.factory = factory;
        FauxNetwork.addToNetwork(PORT_NUMBER,this);
    }
    @Override
    public void receiveMessage(FauxNetwork.Message message) {
        if(message.payload != null)
            handlePieceRequest(message.source,message.payload);
    }
    public void handlePieceRequest(int sender, Object payload){
        int[] coords = (int[])payload;
        Piece piece = factory.createPiece(coords[0],coords[1]);
        FauxNetwork.Message message = new FauxNetwork.Message(PORT_NUMBER,sender,COMMAND,piece);
        FauxNetwork.sendMessage(message);
    }
}
