package client.commands;

import common.network.Request;
import common.network.Response;
import client.Client;

public class ShowCommand extends Command {
    private final Client client;

    public ShowCommand(Client client) {
        super("show", "Показать все элементы в коллекции");
        this.client = client;
    }

    @Override
    public Response execute(Request request) {
        try {
            return client.sendRequest(request);
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении команды show: " + e.getMessage());
        }
    }
} 