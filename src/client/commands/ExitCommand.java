package client.commands;

import client.Client;
import common.network.Request;
import common.network.Response;

public class ExitCommand extends Command {
    private final Client client;

    public ExitCommand(Client client) {
        super("exit", "Сохранить коллекцию в файл и выйти");
        this.client = client;
    }

    @Override
    public Response execute(Request request) {
        try {
            // Отправляем запрос на сервер для сохранения коллекции
            Response response = client.sendRequest(request);
            if (response.isSuccess()) {
                System.out.println(response.getMessage());
                // Завершаем работу клиента
                System.exit(0);
            }
            return response;
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении команды exit: " + e.getMessage());
        }
    }
} 