package multithread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

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
                if (message.startsWith("read@")) {
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
        if (!file.exists())
            return "";
        Scanner sc = new Scanner(file);
        String[] allText = text.split(" ");
        String[] allReadText = sc.nextLine().split("\\p{Blank}");

        List<String> list = new ArrayList<>();
        StringJoiner joiner = new StringJoiner(" ");

        // remove white space
        for (String textToCheck : allReadText) {
            if (textToCheck.equals("") || textToCheck.isBlank())
                continue;
            list.add(textToCheck);
        }


        int index = -1;

        for(int i = 0; i < list.size(); i++) {
            if(checkNext(i, allText, list) != -1) {
                index = i;
                break;
            }
        }
        StringJoiner stringJoiner = new StringJoiner(",");
        stringJoiner.add(index + "");
        if (index != -1) {
            for(int i = 1; i < allText.length; i++) {
                stringJoiner.add((++index) + "");
            }
        }

        return text + "@thisisforsplit@" + stringJoiner.toString();
    }

    private int checkNext(int index, String[] a, List<String> b) {
        for(String s : a) {
            if(!s.equalsIgnoreCase(b.get(index++))) {
                return -1;
            }
        }
        return index;
    }
}
