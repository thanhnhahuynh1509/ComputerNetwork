package multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        Socket socket = null;
        System.out.println("Server listening");
        try ( ServerSocket serverSocket = new ServerSocket(8000)) {
            while (true) {
                socket = serverSocket.accept();
                SocketHandler.clients.add(socket);
                new SocketHandler(socket).start();
            }
        } catch (IOException ex) {

        }
    }
}
