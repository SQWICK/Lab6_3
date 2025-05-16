package server.commands;

import common.network.Response;
import server.collection.CollectionManager;

public class FilterBySalaryCommand extends Command {
    public FilterBySalaryCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute(String[] args, Object data) {
        if (args.length < 1) {
            return new Response(false, "Отсутствует аргумент зарплаты.");
        }
        try {
            Double salary = Double.parseDouble(args[0]);
            var workers = collectionManager.filterBySalary(salary);
            if (workers.isEmpty()) {
                return new Response(true, "Работники с зарплатой больше " + salary + " не найдены.");
            }
            return new Response(true, workers.toString());
        } catch (NumberFormatException e) {
            return new Response(false, "Неверный формат зарплаты.");
        }
    }
} 