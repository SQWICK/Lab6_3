package server.commands;

import common.network.Response;
import server.collection.CollectionManager;

public class InfoCommand extends Command {
    public InfoCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute(String[] args, Object data) {
        return new Response(true, collectionManager.getInfo());
    }
} 