package common.network;

import common.models.Worker;
import java.io.Serializable;
import java.util.LinkedHashSet;

public class Response implements Serializable {
    private final boolean success;
    private final String message;
    private final LinkedHashSet<Worker> collection;

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.collection = null;
    }

    public Response(boolean success, String message, LinkedHashSet<Worker> collection) {
        this.success = success;
        this.message = message;
        this.collection = collection;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public LinkedHashSet<Worker> getCollection() {
        return collection;
    }

    @Override
    public String toString() {
        if (collection != null) {
            StringBuilder sb = new StringBuilder();
            for (Worker worker : collection) {
                sb.append(worker.toString()).append("\n\n");
            }
            return sb.toString().trim();
        }
        return success ? message : "Ошибка: " + message;
    }
} 