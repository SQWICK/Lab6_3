package client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.network.Request;
import common.network.Response;
import common.utils.Config;

public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private final String host;
    private final int port;
    private DatagramSocket socket;
    private InetAddress address;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Client() {
        throw new UnsupportedOperationException("Default constructor should not be used!");
    }

    public void connect() throws Exception {
        socket = new DatagramSocket();
        socket.setSoTimeout(Config.getIntProperty("client.timeout"));
        address = InetAddress.getByName(host);
        logger.info("Connected to server {}:{}", host, port);
    }

    public Response sendRequest(Request request) throws Exception {
        logger.debug("Sending request: {}", request.getCommandName());
        logger.debug("Request data class: {}", request.getData() != null ? request.getData().getClass().getName() : "null");
        if (request.getData() != null) {
            logger.debug("Request data: {}", request.getData());
        }


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(request);
        byte[] sendBuffer = baos.toByteArray();


        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
        socket.send(sendPacket);


        byte[] receiveBuffer = new byte[Config.getIntProperty("client.buffer.size")];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(receivePacket);


        ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Response response = (Response) ois.readObject();

        return response;
    }

    public void disconnect() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        logger.info("Disconnected from server");
    }
} 