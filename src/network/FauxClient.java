package network;

public abstract class FauxClient {
    protected final int PORT_ADDRESS;
    public FauxClient(int port){
        PORT_ADDRESS = port;
    }
    private void sendMessage(int destination, Object payload){
        Message message = new Message(PORT_ADDRESS,destination,payload);
        FauxNetwork.sendMessage(message);
    }
}
