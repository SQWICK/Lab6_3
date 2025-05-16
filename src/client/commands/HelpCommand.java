package client.commands;

import common.network.Request;
import common.network.Response;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "Вывести доступные команды");
    }

    @Override
    public Response execute(Request request) {
        StringBuilder helpMessage = new StringBuilder("Доступные команды:\n");
        helpMessage.append("  help - Показать доступные команды\n");
        helpMessage.append("  info - Показать информацию о коллекции\n");
        helpMessage.append("  show - Показать все элементы в коллекции\n");
        helpMessage.append("  add - Добавить новый элемент в коллекцию\n");
        helpMessage.append("  update <id> - Обновить элемент по его ID\n");
        helpMessage.append("  remove_by_id <id> - Удалить элемент по его ID\n");
        helpMessage.append("  clear - Очистить коллекцию\n");
        helpMessage.append("  print_field_descending_start_date - Вывести даты начала работы в порядке убывания\n");
        helpMessage.append("  filter_by_salary <salary> - Отфильтровать элементы по зарплате\n");
        helpMessage.append("  max_by_name - Показать элемент с максимальным именем\n");
        helpMessage.append("  remove_lower - Удалить элементы меньше указанного\n");
        helpMessage.append("  remove_greater - Удалить элементы больше указанного\n");
        helpMessage.append("  history - Показать последние 11 команд\n");
        helpMessage.append("  exit - Выйти из приложения с сохранением коллекции\n");
        helpMessage.append("  execute_script <path> - Исполнение скрипта команд\n");
        return new Response(true, helpMessage.toString());
    }
} 