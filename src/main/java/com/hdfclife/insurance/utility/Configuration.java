package com.hdfclife.insurance.utility;

/**
 * Reads the different configuration options, may they be in one or more files
 * It implements the singleton pattern and is therefore reused all over the SDK
 * All the values retrieved are cached for later use
 */
public class Configuration {
    private static Configuration instance;
    private PropertiesFileReader fileReader;
    private String configFilePath = "default.properties";

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    /**
     * Upon construction the configuration file is loaded
     */
    private Configuration() {
        fileReader = new PropertiesFileReader(configFilePath);
    }

    /**
     * Obtains the configuration file.
     * Uses values from the configuration file as defaults
     * and allows to be overwritten by environment properties.
     *
     * @param key What to retrieve
     * @return Value of the specified setting
     */
    public String get(String key) {
        String envValue = System.getenv(key);

        // In the TestNG ant task, the value for a environment property that is not set will start with '$'
        if (envValue != null && !envValue.isEmpty() && envValue.charAt(0) != '$') {
            return envValue;
        } else {
            return fileReader.get(key);
        }
    }
}
