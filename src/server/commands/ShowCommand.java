package server.commands;

import common.network.Response;
import common.models.Worker;
import server.collection.CollectionManager;
import java.util.Set;

public class ShowCommand extends Command {
    public ShowCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute(String[] args, Object data) {
        Set<Worker> collection = collectionManager.getCollection();
        if (collection.isEmpty()) {
            return new Response(true, "Коллекция пуста!");
        }
        return new Response(true, collectionManager.getFormattedCollection());
    }
} 