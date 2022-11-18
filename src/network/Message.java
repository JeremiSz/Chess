package network;

public class Message {
    public Message(int source, int destination, Object payload){
        this.source = source;
        this.destination = destination;
        this.payload = payload;
    }
    public int source;
    public int destination;
    public Object payload;
}
