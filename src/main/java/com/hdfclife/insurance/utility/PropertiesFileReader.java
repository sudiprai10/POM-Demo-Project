package com.hdfclife.insurance.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Provides the ability to read values from a properties file, caching the
 * obtained values for later use
 */
public class PropertiesFileReader {
    private Properties parsedProperties;
    private Map<String, String> cachedProperties;

    /**
     * Loads the properties file
     */
    public PropertiesFileReader(String path) {
        cachedProperties = new HashMap<String, String>();
        try {
            parsedProperties = new Properties();
            parsedProperties.load(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't open properties file " + path);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Couldn't process properties file " + path);
            e.printStackTrace();
        }
    }

    /**
     * Obtains the specified value
     *
     * @param key What to retrieve
     * @return Value assigned to that key
     */
    public String get(String key) {
        String value;
        if (!cachedProperties.containsKey(key)) {
            value = parsedProperties.getProperty(key);
            cachedProperties.put(key, value);
        } else {
            value = cachedProperties.get(key);
        }
        return value;
    }
}
