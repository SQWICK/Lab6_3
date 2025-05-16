package client.commands;

import common.network.Request;
import common.network.Response;
import client.Client;

public class MaxByNameCommand extends Command {
    private final Client client;

    public MaxByNameCommand(Client client) {
        super("max_by_name", "Показать элемент с максимальным именем");
        this.client = client;
    }

    @Override
    public Response execute(Request request) {
        try {
            return client.sendRequest(request);
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении команды max_by_name: " + e.getMessage());
        }
    }
} 