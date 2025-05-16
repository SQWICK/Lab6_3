package client.commands;

import client.Client;
import common.models.Worker;
import common.network.Request;
import common.network.Response;

public class UpdateCommand extends Command {
    private final Client client;

    public UpdateCommand(Client client) {
        super("update", "Обновить работника по ID");
        this.client = client;
    }

    @Override
    public Response execute(Request request) {
        try {
            String[] args = request.getArgs();
            if (args.length < 1) {
                return new Response(false, "Пожалуйста, введите ID");
            }

            Long id;
            try {
                id = Long.parseLong(args[0]);
            } catch (NumberFormatException e) {
                return new Response(false, "Неверный формат ID");
            }

            Worker worker = Ask.askWorker(id);
            request.setData(worker);
            return client.sendRequest(request);
        } catch (Ask.AskBreak e) {
            return new Response(false, "Операция отменена: " + e.getMessage());
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении команды update: " + e.getMessage());
        }
    }
} 