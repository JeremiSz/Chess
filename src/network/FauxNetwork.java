package network;

import java.util.HashMap;
public class FauxNetwork {
    private final static HashMap<Integer,FauxServer> servers = new HashMap<>();
    public static void addToNetwork(int address, FauxServer fauxServer){
        servers.put(address,fauxServer);
    }
    public static void removeFromNetwork(int address){
        servers.remove(address);
    }
    public static void sendMessage(Message message){
        FauxServer server = servers.get(message.destination);
        if (server == null) {
            System.err.println("Server not found");
            return;
        }
        server.receiveMessage(message);
    }
    public boolean hasServer(int address){
        return servers.containsKey(address);
    }
}
