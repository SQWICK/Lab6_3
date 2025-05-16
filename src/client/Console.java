package client;

import java.util.Scanner;

import client.commands.CommandManager;
import common.network.Request;
import common.network.Response;

public class Console {
    private final CommandManager commandManager;
    private final Scanner scanner;
    private boolean running;

    public Console(CommandManager commandManager) {
        this.commandManager = commandManager;
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    public void start() {
        System.out.println("Добро пожаловать в программу для управления коллекцией работников!");
        System.out.println("Введите 'help' для просмотра доступных команд.");

        while (running) {
            try {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    continue;
                }

                String[] parts = input.split("\\s+", 2);
                String commandName = parts[0].toLowerCase();
                String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

                Request request = new Request(commandName, args, null);
                Response response = commandManager.executeCommand(request);

                if (response.isSuccess()) {
                    System.out.println(response.getMessage());
                } else {
                    System.err.println("Ошибка: " + response.getMessage());
                }
            } catch (Exception e) {
                if (running) {
                    System.err.println("Ошибка: " + e.getMessage());
                }
            }
        }
    }

    public void stop() {
        running = false;
        if (scanner != null) {
            scanner.close();
        }
    }
} 