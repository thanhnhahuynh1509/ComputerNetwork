package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    private static final int PORT = 8000;

    private InputStream is;
    private OutputStream os;

    private final ServerSocket serverSocket;

    public ServerTCP(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void waitForClientConnect() throws IOException {
        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");
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
        byte[] a = new byte[5];
        try {
            BufferedInputStream bis = new BufferedInputStream(is);
            int i;
            while((i = bis.read(a, 0, 5)) != -1) {
                sb.append(new String(a, 0, i));
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
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ServerTCP serverTCP = null;
        try {
            serverTCP = new ServerTCP(PORT);
            serverTCP.waitForClientConnect();

            while(true) {
                int message = Integer.parseInt(serverTCP.receive());
                System.out.println("Message: " + message);
                serverTCP.send(Fibonacci.getFi(message) + "");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(serverTCP != null)
                serverTCP.close();
        }
    }
}
