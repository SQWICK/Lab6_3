package server.commands;

import common.network.Response;
import server.collection.CollectionManager;

public class MaxByNameCommand extends Command {
    public MaxByNameCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute(String[] args, Object data) {
        var maxWorker = collectionManager.getMaxByName();
        if (maxWorker == null) {
            return new Response(false, "Коллекция пуста.");
        }
        return new Response(true, maxWorker.toString());
    }
} 