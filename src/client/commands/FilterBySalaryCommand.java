package client.commands;

import common.network.Request;
import common.network.Response;
import client.Client;

public class FilterBySalaryCommand extends Command {
    private final Client client;

    public FilterBySalaryCommand(Client client) {
        super("filter_by_salary", "Отфильтровать элементы по зарплате");
        this.client = client;
    }

    @Override
    public Response execute(Request request) {
        try {
            return client.sendRequest(request);
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении команды filter_by_salary: " + e.getMessage());
        }
    }
} 