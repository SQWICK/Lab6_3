package server.commands;

import common.file_handlers.XMLWriter;
import common.network.Response;
import server.Server;
import server.collection.CollectionManager;

public class ExitCommand extends Command {
    private final Server server;
    private final XMLWriter xmlWriter;

    public ExitCommand(CollectionManager collectionManager, Server server) {
        super(collectionManager);
        this.server = server;
        this.xmlWriter = new XMLWriter();
    }

    @Override
    public Response execute(String[] args, Object data) {
        try {
            xmlWriter.write(collectionManager.getCollection(), collectionManager.getFilePath());
            return new Response(true, "Коллекция сохранена успешно");
        } catch (Exception e) {
            return new Response(false, "Ошибка при сохранении коллекции: " + e.getMessage());
        }
    }
} 