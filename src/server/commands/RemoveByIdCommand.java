package server.commands;

import common.network.Response;
import server.collection.CollectionManager;

public class RemoveByIdCommand extends Command {
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute(String[] args, Object data) {
        if (args.length < 1) {
            return new Response(false, "Отсутствует аргумент ID.");
        }
        try {
            Long id = Long.parseLong(args[0]);
            collectionManager.remove(id);
            return new Response(true, "Работник удален успешно.");
        } catch (NumberFormatException e) {
            return new Response(false, "Неверный формат ID.");
        }
    }
} 