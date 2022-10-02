package udp;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientUDP {
    private final DatagramSocket socket;
    private DatagramPacket senderPacket;
    private DatagramPacket receivePacket;

    public ClientUDP() throws SocketException {
        socket = new DatagramSocket();
        System.out.println("Client has turned on");
    }

    public void send(Object obj) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            byte[] data = baos.toByteArray();
            senderPacket = new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), 8000);
            socket.send(senderPacket);
        }
    }

    public Object receive() throws IOException, ClassNotFoundException {
        byte[] data = new byte[1024];
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        Object result = null;
        try {
            receivePacket = new DatagramPacket(data, data.length);
            socket.receive(receivePacket);
            bais = new ByteArrayInputStream(receivePacket.getData());
            ois = new ObjectInputStream(bais);
            result = ois.readObject();
        } finally {
            if (bais != null)
                bais.close();
            if (ois != null)
                ois.close();
        }
        return result;
    }

    public void close() {
        socket.close();
    }

    public static void main(String[] args) {
        try {
            ClientUDP clientUDP = new ClientUDP();
            clientUDP.send("Client: this is message from client");
            Object obj = clientUDP.receive();
            System.out.println(obj);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End");
    }
}
