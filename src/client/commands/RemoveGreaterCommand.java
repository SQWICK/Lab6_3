package client.commands;

import common.network.Request;
import common.network.Response;
import client.Client;

public class RemoveGreaterCommand extends Command {
    private final Client client;

    public RemoveGreaterCommand(Client client) {
        super("remove_greater", "Удалить элементы с ID больше указанного");
        this.client = client;
    }

    @Override
    public Response execute(Request request) {
        try {
            String[] args = request.getArgs();
            if (args.length < 1) {
                return new Response(false, "Пожалуйста, введите ID");
            }

            try {
                Long id = Long.parseLong(args[0]);
            return client.sendRequest(request);
            } catch (NumberFormatException e) {
                return new Response(false, "Неверный формат ID");
            }
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении команды remove_greater: " + e.getMessage());
        }
    }
} 