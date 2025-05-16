package server.commands;

import common.network.Response;
import server.collection.CollectionManager;

public class ExecuteScriptCommand extends Command {
    public ExecuteScriptCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public Response execute(String[] args, Object data) {
        // Server-side execute_script command is not needed as script execution is handled on the client side
        return new Response(false, "команда execute_script должна выполняться на стороне клиента");
    }
} 