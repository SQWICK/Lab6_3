package server.commands;

import common.network.Response;
import server.collection.CollectionManager;

public class HelpCommand extends Command {
    public HelpCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute(String[] args, Object data) {
        StringBuilder helpMessage = new StringBuilder("Доступные команды:\n");
        helpMessage.append("info - отобразить информацию о коллекции\n");
        helpMessage.append("show - отобразить все элементы коллекции\n");
        helpMessage.append("add - добавить новый элемент в коллекцию\n");
        helpMessage.append("update <id> - обновить элемент с указанным id\n");
        helpMessage.append("remove_by_id <id> - удалить элемент с указанным id\n");
        helpMessage.append("clear - очистить коллекцию\n");
        helpMessage.append("remove_greater - удалить все элементы больше указанного\n");
        helpMessage.append("remove_lower - удалить все элементы меньше указанного\n");
        helpMessage.append("max_by_name - отобразить элемент с максимальным именем\n");
        helpMessage.append("filter_by_salary <salary> - отобразить элементы с зарплатой больше указанной\n");
        helpMessage.append("print_field_descending_start_date - отобразить даты начала работы в порядке убывания\n");
        helpMessage.append("help - отобразить это сообщение\n");
        helpMessage.append("exit - выйти из программы\n");
        return new Response(true, helpMessage.toString());
    }
} 