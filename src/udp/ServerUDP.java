package udp;

import bai1.Converter;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Map;

public class ServerUDP {
    private final DatagramSocket socket;
    private DatagramPacket senderPacket;
    private DatagramPacket receivePacket;

    public ServerUDP(int port) throws SocketException {
        socket = new DatagramSocket(port);
        System.out.println("Server has turned on");
    }

    public void send(Object obj) throws IOException {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            byte[] data = baos.toByteArray();
            senderPacket = new DatagramPacket(data, data.length, receivePacket.getAddress(), receivePacket.getPort());
            socket.send(senderPacket);
        } finally {
            if (baos != null)
                baos.close();
            if (oos != null)
                oos.close();
        }
    }

    public Object receive() throws IOException, ClassNotFoundException {
        byte[] data = new byte[1024];
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        Object result;
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
            ServerUDP serverUDP = new ServerUDP(8000);
            while(true) {
                Map<Integer, Integer> obj = (Map<Integer, Integer>) serverUDP.receive();
                String result = "";
                for(Integer key : obj.keySet()) {
                    result = Converter.convert(key, obj.get(key));
                }
                serverUDP.send(result);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
