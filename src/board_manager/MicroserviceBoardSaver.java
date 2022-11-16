package board_manager;
import microservice.FauxNetwork;
import microservice.FauxServer;

import java.io.IOException;

public class MicroserviceBoardSaver implements FauxServer {
    private static final int PORT_NUMBER = 65534;
    private static final String FOUND = "found";

    private BoardSaver boardSaver;

    public MicroserviceBoardSaver(BoardSaver boardSaver){
       this.boardSaver = boardSaver;
       FauxNetwork.addToNetwork(PORT_NUMBER,this);
    }
    @Override
    public void receiveMessage(FauxNetwork.Message message) {
        if (message.command.isEmpty())
            return;

        if (message.payload == null) {
            handleLoad(message);
        }
        else {
            handleSave(message);
        }
    }

    private void handleSave(FauxNetwork.Message message) {
        try {
            boardSaver.save(message.command, message.payload);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.printf(e.getMessage());
        }
    }

    private void handleLoad(FauxNetwork.Message message) {
        Object payload;
        try {
            payload = boardSaver.load(message.command);
        } catch (IOException | ClassNotFoundException e) {
            payload = null;
        }
        FauxNetwork.Message returnMessage = new FauxNetwork.Message(
                PORT_NUMBER,
                message.source,
                FOUND,
                payload);

        FauxNetwork.sendMessage(returnMessage);
    }
}
