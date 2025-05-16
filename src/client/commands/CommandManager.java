package client.commands;

import common.network.Request;
import common.network.Response;
import client.Client;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands;
    private final Client client;

    public CommandManager(Client client) {
        this.client = client;
        this.commands = new HashMap<>();
        registerCommands();
    }

    private void registerCommands() {
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand(client));
        commands.put("show", new ShowCommand(client));
        commands.put("add", new AddCommand(client));
        commands.put("update", new UpdateCommand(client));
        commands.put("remove_by_id", new RemoveByIdCommand(client));
        commands.put("clear", new ClearCommand(client));
        commands.put("print_field_descending_start_date", new PrintFieldDescendingStartDateCommand(client));
        commands.put("filter_by_salary", new FilterBySalaryCommand(client));
        commands.put("max_by_name", new MaxByNameCommand(client));
        commands.put("remove_lower", new RemoveLowerCommand(client));
        commands.put("remove_greater", new RemoveGreaterCommand(client));
        commands.put("history", new HistoryCommand(client));
        commands.put("exit", new ExitCommand(client));
        commands.put("execute_script", new ExecuteScriptCommand(client));
    }

    public Response executeCommand(Request request) {
        String commandName = request.getCommandName().toLowerCase();
        Command command = commands.get(commandName);
        if (command == null) {
            return new Response(false, "Неизвестная команда: " + commandName);
        }
        return command.execute(request);
    }
} 