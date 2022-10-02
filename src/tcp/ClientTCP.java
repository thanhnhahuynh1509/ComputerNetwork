package tcp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {
    private static final int PORT = 8000;
    private static final String HOST = "localhost";
    private final Socket socket;

    private final InputStream is;
    private final OutputStream os;

    public ClientTCP(String host, int port) throws IOException {
        socket = new Socket(host, port);
        os = socket.getOutputStream();
        is = socket.getInputStream();
    }

    public void send(String message) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(os);
            bos.write(message.getBytes());
            bos.flush();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public String receive() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedInputStream bis = new BufferedInputStream(is);
            int i;
            while((i = bis.read()) != -1 ) {
                sb.append((char) i);
                if( bis.available() <= 0)
                    break;
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return sb.toString();
    }

    public void close() {
        try {
            if(is != null)
                is.close();
            if(os != null)
                os.close();
            if(socket != null)
                socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ClientTCP clientTCP = null;
        try {
            clientTCP = new ClientTCP(HOST, PORT);
            System.out.print("Nhập số n: ");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            clientTCP.send(n + "");
            System.out.println("Số fibonacci thứ " + n + " là: " + clientTCP.receive());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(clientTCP != null)
                clientTCP.close();
        }
    }
}
