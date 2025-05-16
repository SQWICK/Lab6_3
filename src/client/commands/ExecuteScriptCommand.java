package client.commands;

import common.network.Request;
import common.network.Response;
import client.Client;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class ExecuteScriptCommand extends Command {
    private final Client client;
    private static final Stack<String> scriptStack = new Stack<>();

    public ExecuteScriptCommand(Client client) {
        super("execute_script", "Выполнить команды из скрипта");
        this.client = client;
    }

    @Override
    public Response execute(Request request) {
        try {
            String[] args = request.getArgs();
            if (args.length < 1) {
                return new Response(false, "Пожалуйста, введите путь к скрипту");
            }

            String scriptPath = args[0];
            
            // Check if this script is already being executed (prevent recursion)
            if (scriptStack.contains(scriptPath)) {
                return new Response(false, "Рекурсивное выполнение скрипта не разрешено: " + scriptPath);
            }

            scriptStack.push(scriptPath);
            try (BufferedReader reader = new BufferedReader(new FileReader(scriptPath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;

                    String[] parts = line.split("\\s+", 2);
                    String commandName = parts[0].toLowerCase();
                    String[] commandArgs = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

                    Request scriptRequest = new Request(commandName, commandArgs, null);
                    Response response = client.sendRequest(scriptRequest);
                    
                    if (!response.isSuccess()) {
                        return new Response(false, "Неверная команда: '" + line + "': " + response.getMessage());
                    }
                    
                    System.out.println(response.getMessage());
                }
            } catch (IOException e) {
                return new Response(false, "Ошибка при чтении скрипта: " + e.getMessage());
            } finally {
                scriptStack.pop();
            }

            return new Response(true, "Скрипт выполнен успешно:");
        } catch (Exception e) {
            return new Response(false, "Ошибка при выполнении скрипта: " + e.getMessage());
        }
    }
} 