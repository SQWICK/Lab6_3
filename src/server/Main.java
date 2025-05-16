package server;

import common.file_handlers.XMLReader;
import common.utils.Config;
import server.collection.CollectionManager;
import server.commands.CommandManager;

public class Main {
    public static void main(String[] args) {
        try {
            Config.initialize();
            
            String inputPath = System.getenv("FILE_NAME");
            if (inputPath == null) {
                System.err.println("Не указан путь к файлу в переменной окружения FILE_NAME");
                System.exit(1);
            }

            int port = args.length > 0 ? Integer.parseInt(args[0]) : Config.getIntProperty("server.port");
            System.out.println("Starting server on port " + port);

            XMLReader xmlReader = new XMLReader(inputPath);
            CollectionManager collectionManager = new CollectionManager(xmlReader);
            CommandManager commandManager = new CommandManager(collectionManager, null);
            Server server = new Server(port, collectionManager, commandManager);

            // Add shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down server...");
                server.stop();
            }));

            // Start server
            server.start();
        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
} 