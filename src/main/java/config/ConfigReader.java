package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();

    static {
        String path = "src/main/resources/config.properties";
        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config: " + path, e);
        }
    }

    public static String get(String key) {
        String val = props.getProperty(key);
        if (val == null) throw new RuntimeException("Missing config key: " + key);
        return val;
    }
}
