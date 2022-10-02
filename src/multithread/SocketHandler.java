package multithread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocketHandler extends Thread {

    public static List<Socket> clients = new ArrayList<>();
    private final Socket socket;
    private Scanner in;
    private PrintWriter out;
    private String name;

    public SocketHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new Scanner(socket.getInputStream());
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.name = in.nextLine();
    }

    @Override
    public void run() {
        System.out.println(name + " connected");
        try {
            while (true) {
                String message = in.nextLine();
                if(message.startsWith("read@")) {
                    message = message.replace("read@", "");
                    String[] options = message.split("@thisisforsplit@");
                    String path = options[0];
                    String textForFind = options[1];
                    send(findTextOfFile(path, textForFind), socket);
                } else {
                    broadcast(message);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void broadcast(String message) throws IOException {
        for (Socket client : clients) {
            send(message, client);
        }
    }

    private void send(String message, Socket socket) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(message);
    }

    private String findTextOfFile(String filePath, String text) throws FileNotFoundException {
        File file = new File(filePath);
        if(!file.exists())
            return "";
        Scanner sc = new Scanner(file);
        String[] allText = sc.nextLine().split("\\p{Blank}");

        List<String> list = new ArrayList<>();

        for(String textToCheck : allText) {
            if(textToCheck.equals("") || textToCheck.isBlank())
                continue;
            list.add(textToCheck);
        }

        return text + "@thisisforsplit@" + list.indexOf(text);
    }
}
