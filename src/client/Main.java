package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.commands.CommandManager;
import common.utils.Config;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            Config.initialize();
            
            String host = args.length > 0 ? args[0] : Config.getProperty("server.host");
            int port = args.length > 1 ? Integer.parseInt(args[1]) : Config.getIntProperty("server.port");
            
            logger.info("Connecting to server {}:{}", host, port);

            Client client = new Client(host, port);
            client.connect();

            CommandManager commandManager = new CommandManager(client);
            Console console = new Console(commandManager);
            
            // Add shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("Shutting down client...");
                console.stop();
                client.disconnect();
            }));

            // Start console
            console.start();
        } catch (Exception e) {
            logger.error("Fatal error: {}", e.getMessage(), e);
            System.exit(1);
        }
    }
} 