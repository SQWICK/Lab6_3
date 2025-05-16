package client.commands;

import common.network.Request;
import common.network.Response;
import client.Client;

public class ClearCommand extends Command {
    private final Client client;

    public ClearCommand(Client client) {
        super("clear", "Очистить коллекцию");
        this.client = client;
    }

    @Override
    public Response execute(Request request) {
        try {
            return client.sendRequest(request);
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении команды clear: " + e.getMessage());
        }
    }
} 