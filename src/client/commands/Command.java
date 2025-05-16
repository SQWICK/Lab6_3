package client.commands;

import common.network.Request;
import common.network.Response;

public abstract class Command {
    protected final String name;
    protected final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract Response execute(Request request);
} 