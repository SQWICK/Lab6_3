package server.commands;

import common.models.Worker;
import common.network.Response;
import server.collection.CollectionManager;

public class AddCommand extends Command {
    public AddCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute(String[] args, Object data) {
        if (!(data instanceof Worker)) {
            return new Response(false, "Неверный тип данных для команды add.");
        }
        Worker worker = (Worker) data;
        collectionManager.add(worker);
        return new Response(true, "Работник успешно добавлен.");
    }
} 