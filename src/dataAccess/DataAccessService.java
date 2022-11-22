package dataAccess;

import Data.Board;
import network.FauxNetwork;
import network.FauxServer;
import network.Message;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DataAccessService extends FauxServer {
    private final int PORT_ADDRESS;
    private final DataAccess dataAccess;
    public DataAccessService(int port){
        super(port);
        PORT_ADDRESS = port;
        this.dataAccess = new DataAccessFile();
    }

    @Override
    public void receiveMessage(Message message) {
        Class payloadClass = message.payload.getClass();
        if (payloadClass.equals(String.class)) {
            handleLoad((String) message.payload, message.source);
        } else if (payloadClass.equals(Board.class)) {
            handleSave((Board) message.payload);
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
    private void handleSave(Board board){
        try {
            dataAccess.save(board);
        }
        catch (IOException e){
            e.printStackTrace();
            System.err.println("Failed to save");
        }
    }
}
