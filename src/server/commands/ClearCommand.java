package server.commands;

import common.network.Response;
import server.collection.CollectionManager;

public class ClearCommand extends Command {
    public ClearCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute(String[] args, Object data) {
        collectionManager.clear();
        return new Response(true, "Коллекция очищена успешно.");
    }
} 