package microservice;

import java.util.HashMap;

public class FauxNetwork {
    public static class Message{
        public Message(int source,int target, String command, Object payload){
            this.source = source;
            this.target = target;
            this.command = command;
            this.payload = payload;
        }
        public int source;
        public int target;
        public String command;
        public Object payload;
    }
    private final static HashMap<Integer,FauxServer> servers = new HashMap<>();
    public static void addToNetwork(int address, FauxServer fauxServer){
        servers.put(address,fauxServer);
    }
    public static void removeFromNetwork(int address){
        servers.remove(address);
    }
    public static void sendMessage(Message message){
        FauxServer server = servers.get(message.target);
        if (server == null) {
            System.err.println("Server not found");
            return;
        }
        server.receiveMessage(message);
    }
}
