package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
    private static final Logger log = LoggerFactory.getLogger(ConfigReader.class);

    static {
        try {

            String localPropsFile = "local.properties";
            String globalPropsFile = "global.properties";

            InputStream localPropsInput = ConfigReader.class.getClassLoader().getResourceAsStream(localPropsFile);

            if(localPropsInput != null) {
                properties.load(localPropsInput);
            } else {
                log.warn("local.properties not found, skipping");
            }

            // env задается параметром -Denv при запуске тестов
            // по умолчанию будет dev (второй параметр System.getProperty)
            String envConfig = System.getProperty("env", properties.getProperty("env", "at"));
            String envPropsFile = "config-" + envConfig + ".properties";
            log.info("Запуск через config: {}", envPropsFile);

            InputStream envPropsInput = ConfigReader.class.getClassLoader().getResourceAsStream(envPropsFile);
            InputStream globalPropsInput = ConfigReader.class.getClassLoader().getResourceAsStream(globalPropsFile);

            if (envPropsInput == null) {
                throw new RuntimeException("Config file not found: " + envPropsFile);
            }

            if (globalPropsInput == null) {
                throw new RuntimeException("Config file not found: " + globalPropsFile);
            }

            properties.load(envPropsInput);
            properties.load(globalPropsInput);

        } catch (Exception e) {
            log.error("Failed to load config", e);
            throw new RuntimeException("Failed to load config", e);
        }
    }

    // при вызове этого метода, будет приоритетом значение которое было передедано в -D
    // значение -D есть - берем его, если нет то берем из config-x.properties
    public static String get(String key) {
        return System.getProperty(key, properties.getProperty(key));
    }
}