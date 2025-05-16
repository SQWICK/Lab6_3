package client.commands;

import client.Client;
import common.models.Worker;
import common.network.Request;
import common.network.Response;

public class AddCommand extends Command {
    private final Client client;

    public AddCommand(Client client) {
        super("add", "Добавить нового работника в коллекцию");
        this.client = client;
    }

    @Override
    public Response execute(Request request) {
        try {
            Worker worker = Ask.askWorker(null);
            request.setData(worker);
            return client.sendRequest(request);
        } catch (Ask.AskBreak e) {
            return new Response(false, "Операция отменена: " + e.getMessage());
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении команды add: " + e.getMessage());
        }
    }
} 