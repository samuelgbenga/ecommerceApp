package main.ecommerce.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");

            }

            // Load the properties file from the classpath
            properties.load(input);

        } catch (IOException ex) {
            ex.fillInStackTrace();
        }
    }

    // Method to get property value by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
