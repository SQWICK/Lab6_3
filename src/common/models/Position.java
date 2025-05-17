package common.models;

public enum Position {
    MANAGER,
    HEAD_OF_DIVISION,
    BAKER,
    COOK,
    MANAGER_OF_CLEANING,
    WORKER_OF_YEAR;

    public static String names() {
        StringBuilder positionList = new StringBuilder();
        for (Position pos : Position.values()) {
            positionList.append(pos.name()).append(", ");
        }
        return positionList.substring(0, positionList.length() - 2);
    }

    public static Position fromString(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        try {
            return Position.valueOf(text.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
} 