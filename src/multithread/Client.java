package multithread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final Socket socket;
    private Scanner in;
    private PrintWriter out;

    public Client(int port) throws IOException {
        this.socket = new Socket("localhost", port);
        this.in = new Scanner(socket.getInputStream());
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void send(String message) {
        out.println(message);
    }

    public String receive() {
        return in.nextLine();
    }



}
