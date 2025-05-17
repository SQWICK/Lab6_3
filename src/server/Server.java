package server;

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
import server.collection.CollectionManager;
import server.commands.CommandManager;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    private final int port;
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;
    private DatagramSocket socket;
    private boolean running;

    public Server(int port, CollectionManager collectionManager, CommandManager commandManager) {
        this.port = port;
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
        this.running = false;
    }

    public void start() {
        try {
            socket = new DatagramSocket(port);
            running = true;
            logger.info("Server started on port {}", port);

            while (running) {
                byte[] receiveBuffer = new byte[Config.getIntProperty("client.buffer.size")];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);


                ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Request request = (Request) ois.readObject();

                logger.debug("Received request: {}", request.getCommandName());


                Response response = commandManager.executeCommand(request.getCommandName(), request.getArgs(), request.getData());


                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(response);
                byte[] sendBuffer = baos.toByteArray();


                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                socket.send(sendPacket);

                logger.debug("Sent response to {}:{}", clientAddress, clientPort);
            }
        } catch (Exception e) {
            logger.error("Server error: {}", e.getMessage(), e);
        } finally {
            stop();
        }
    }

    public void stop() {
        running = false;
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        logger.info("Server stopped");
    }
} 