package client.commands;

import common.network.Request;
import common.network.Response;
import client.Client;

public class HistoryCommand extends Command {
    private final Client client;

    public HistoryCommand(Client client) {
        super("history", "Показать историю команд");
        this.client = client;
    }

    @Override
    public Response execute(Request request) {
        try {
            return client.sendRequest(request);
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении команды history: " + e.getMessage());
        }
    }
} 