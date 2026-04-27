package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try {
            // env задается параметром -Denv при запуске тестов
            // по умолчанию будет dev (второй параметр System.getProperty)
            String env = System.getProperty("env", "dev");
            String fileName = "config-" + env + ".properties";

            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream(fileName);

            if (input == null) {
                throw new RuntimeException("Config file not found: " + fileName);
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    // при вызове этого метода, будет приоритетом значение которое было передедано в -D
    // значение -D есть - берем его, если нет то берем из config-x.properties
    public static String get(String key) {
        return System.getProperty(key, properties.getProperty(key));
    }
}