package network;

public abstract class FauxServer {
    public FauxServer(int port){
        FauxNetwork.addToNetwork(port,this);
    }
    public  abstract void receiveMessage(Message message);
}
