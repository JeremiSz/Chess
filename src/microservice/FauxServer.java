package microservice;

public interface FauxServer {
    void receiveMessage(FauxNetwork.Message message);
}
