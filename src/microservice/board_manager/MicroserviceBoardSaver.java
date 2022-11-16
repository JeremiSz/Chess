package microservice.board_manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MicroserviceBoardSaver {
    private static final int PORT_NUMBER = 65535;
    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket(PORT_NUMBER)) {
            Socket client =  socket.accept();
            InputStream in = client.getInputStream();
            in.read
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
