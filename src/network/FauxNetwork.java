package network;

import java.util.HashMap;
public class FauxNetwork {
    private static final int ADDRESS_MASK = 65535;
    private final static HashMap<Integer,FauxServer> servers = new HashMap<>();
    public static void addToNetwork(int address, FauxServer fauxServer){
        servers.put(address,fauxServer);
    }
    public static void removeFromNetwork(int address){
        servers.remove(address);
    }
    public static void sendMessage(Message message){
        int destination = message.destination & ADDRESS_MASK;
        FauxServer server = servers.get(destination);
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
