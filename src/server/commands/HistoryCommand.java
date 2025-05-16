package server.commands;

import common.network.Response;
import server.collection.CollectionManager;
import java.util.LinkedList;

public class HistoryCommand extends Command {
    private final LinkedList<String> commandHistory;

    public HistoryCommand(CollectionManager collectionManager, LinkedList<String> commandHistory) {
        super(collectionManager);
        this.commandHistory = commandHistory;
    }

    @Override
    public Response execute(String[] args, Object data) {
        StringBuilder result = new StringBuilder();
        result.append("Последние ").append(commandHistory.size()).append(" команд:\n");
        
        int i = 1;
        for (String command : commandHistory) {
            result.append(i++).append(". ").append(command).append("\n");
        }
        
        return new Response(true, result.toString());
    }
} 