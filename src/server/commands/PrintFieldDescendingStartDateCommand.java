package server.commands;

import common.network.Response;
import server.collection.CollectionManager;

public class PrintFieldDescendingStartDateCommand extends Command {
    public PrintFieldDescendingStartDateCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute(String[] args, Object data) {
        var dates = collectionManager.getDescendingStartDates();
        if (dates.isEmpty()) {
            return new Response(true, "Не найдены начальные даты.");
        }
        return new Response(true, dates.toString());
    }
} 