package server.commands;

import common.network.Response;
import server.collection.CollectionManager;

public abstract class Command {
    protected final CollectionManager collectionManager;

    public Command(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public abstract Response execute(String[] args, Object data);
} 