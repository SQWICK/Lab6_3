package common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();
    private static boolean initialized = false;

    public static void initialize() {
        if (!initialized) {
            try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    throw new RuntimeException("Unable to find config.properties");
                }
                properties.load(input);
                initialized = true;
            } catch (IOException e) {
                throw new RuntimeException("Error loading config.properties", e);
            }
        }
    }

    public static String getProperty(String key) {
        if (!initialized) {
            initialize();
        }
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
} 