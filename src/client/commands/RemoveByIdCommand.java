package client.commands;

import common.network.Request;
import common.network.Response;
import client.Client;

public class RemoveByIdCommand extends Command {
    private final Client client;

    public RemoveByIdCommand(Client client) {
        super("remove_by_id", "Удалить элемент по его ID");
        this.client = client;
    }

    @Override
    public Response execute(Request request) {
        try {
            return client.sendRequest(request);
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении команды remove_by_id: " + e.getMessage());
        }
    }
} 