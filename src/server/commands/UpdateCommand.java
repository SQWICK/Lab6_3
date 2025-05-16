package server.commands;

import common.models.Worker;
import common.network.Response;
import server.collection.CollectionManager;

public class UpdateCommand extends Command {
    public UpdateCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute(String[] args, Object data) {
        if (args.length < 1) {
            return new Response(false, "Отсутствует аргумент ID.");
        }
        if (!(data instanceof Worker)) {
            return new Response(false, "Неверный тип данных. Ожидаемый Worker.");
        }
        try {
            Long id = Long.parseLong(args[0]);
            Worker worker = (Worker) data;
            worker.setId(id);
            collectionManager.update(id, worker);
            return new Response(true, "Работник обновлен успешно.");
        } catch (NumberFormatException e) {
            return new Response(false, "Неверный формат ID.");
        }
    }
} 