package dataAccess;

import network.FauxNetwork;
import network.FauxServer;
import network.Message;

import java.io.FileNotFoundException;

public class DataAccessService extends FauxServer {
    private final int PORT_ADDRESS;
    private final DataAccess dataAccess;
    public DataAccessService(int port, DataAccess dataAccess){
        super(port);
        PORT_ADDRESS = port;
        this.dataAccess = dataAccess;
    }

    @Override
    public void receiveMessage(Message message) {
        if(message.payload.getClass().equals(String.class)){
            handleLoad((String) message.payload,message.source);
        }
    }
    private void handleLoad(String name,int source){
        Object state;
        try {
            state = dataAccess.load(name);
            Board board = new Board(name,state);

            FauxNetwork.sendMessage(new Message(PORT_ADDRESS,source,board));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            System.err.println("Error failed");
        }
    }
}
