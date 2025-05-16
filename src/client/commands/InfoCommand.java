package client.commands;

import common.network.Request;
import common.network.Response;
import client.Client;

public class InfoCommand extends Command {
    private final Client client;

    public InfoCommand(Client client) {
        super("info", "Показать информацию о коллекции");
        this.client = client;
    }

    @Override
    public Response execute(Request request) {
        try {
            return client.sendRequest(request);
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении команды info: " + e.getMessage());
        }
    }
} 