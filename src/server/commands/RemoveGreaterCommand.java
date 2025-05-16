package server.commands;

import common.network.Response;
import server.collection.CollectionManager;

public class RemoveGreaterCommand extends Command {
    public RemoveGreaterCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute(String[] args, Object data) {
        if (args.length < 1) {
            return new Response(false, "Отсутствует аргумент ID.");
        }
        try {
            Long id = Long.parseLong(args[0]);
            collectionManager.removeGreater(id);
            return new Response(true, "Работники с ID больше " + id + " удалены успешно.");
        } catch (NumberFormatException e) {
            return new Response(false, "Неверный формат ID.");
        }
    }
} 