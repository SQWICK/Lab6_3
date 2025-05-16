package common.network;

import java.io.Serializable;

public class Request implements Serializable {
    private String commandName;
    private String[] args;
    private Object data;

    public Request(String commandName, String[] args, Object data) {
        this.commandName = commandName;
        this.args = args;
        this.data = data;
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getArgs() {
        return args;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
} 