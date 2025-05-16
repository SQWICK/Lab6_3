package client.commands;

import common.network.Request;
import common.network.Response;
import client.Client;

public class PrintFieldDescendingStartDateCommand extends Command {
    private final Client client;

    public PrintFieldDescendingStartDateCommand(Client client) {
        super("print_field_descending_start_date", "Вывести даты начала работы в порядке убывания");
        this.client = client;
    }

    @Override
    public Response execute(Request request) {
        try {
            return client.sendRequest(request);
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении команды print_field_descending_start_date: " + e.getMessage());
        }
    }
} 