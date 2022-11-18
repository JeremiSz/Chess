package frontEnd;

import network.FauxServer;
import network.Message;

public class FrontEndService extends FauxServer {
    public FrontEndService(int port) {
        super(port);
    }

    @Override
    public void receiveMessage(Message message) {

    }
}
