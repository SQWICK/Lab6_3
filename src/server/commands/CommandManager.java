package server.commands;

import common.network.Response;
import server.collection.CollectionManager;
import server.Server;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public class CommandManager {
    private final Map<String, Command> commands;
    private final CollectionManager collectionManager;
    private final LinkedList<String> commandHistory;
    private final Server server;

    public CommandManager(CollectionManager collectionManager, Server server) {
        this.collectionManager = collectionManager;
        this.server = server;
        this.commands = new HashMap<>();
        this.commandHistory = new LinkedList<>();

        // Register commands
        commands.put("help", new HelpCommand(collectionManager));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager));
        commands.put("update", new UpdateCommand(collectionManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("remove_greater", new RemoveGreaterCommand(collectionManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commands.put("max_by_name", new MaxByNameCommand(collectionManager));
        commands.put("filter_by_salary", new FilterBySalaryCommand(collectionManager));
        commands.put("print_field_descending_start_date", new PrintFieldDescendingStartDateCommand(collectionManager));
        commands.put("history", new HistoryCommand(collectionManager, commandHistory));
        commands.put("exit", new ExitCommand(collectionManager, server));
        commands.put("execute_script", new ExecuteScriptCommand(collectionManager));
    }

    public Response executeCommand(String commandName, String[] args, Object data) {
        Command command = commands.get(commandName.toLowerCase());
        if (command == null) {
            return new Response(false, "Неизвестная команда. Используйте 'help' для просмотра доступных команд.");
        }

        try {
            if (!commandName.equals("history") && !commandName.equals("exit")) {
                commandHistory.add(commandName);
                if (commandHistory.size() > 11) {
                    commandHistory.removeFirst();
                }
            }
            return command.execute(args, data);
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении команды: " + e.getMessage());
        }
    }
} 