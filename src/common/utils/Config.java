package common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();
    private static boolean initialized = false;

    public static void initialize() {
        if (!initialized) {
            try {
                // First try to load from classpath (when running from JAR)
                InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties");
                
                // If not found in classpath, try to load from the same directory as the JAR
                if (input == null) {
                    // Get the path to the JAR file
                    String jarPath = Config.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                    // Fix Windows path
                    if (jarPath.startsWith("/")) {
                        jarPath = jarPath.substring(1);
                    }
                    // Get the directory containing the JAR
                    Path jarDir = Paths.get(jarPath).getParent();
                    // Look for config.properties in that directory
                    File configFile = jarDir.resolve("config.properties").toFile();
                    
                    if (configFile.exists()) {
                        input = new FileInputStream(configFile);
                    } else {
                        // Try current working directory as fallback
                        File currentDirConfig = new File("config.properties");
                        if (currentDirConfig.exists()) {
                            input = new FileInputStream(currentDirConfig);
                        } else {
                            throw new RuntimeException("Unable to find config.properties. Please ensure it is in the same directory as the JAR file: " + jarDir);
                        }
                    }
                }
                
                properties.load(input);
                input.close();
                initialized = true;
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException("Error loading config.properties: " + e.getMessage(), e);
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