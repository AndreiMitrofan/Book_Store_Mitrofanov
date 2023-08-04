package com.demoqa.automation.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {
    private static final ClassLoader CLASS_LOADER = PropertyUtils.class.getClassLoader();
    private static final String PROPERTIES_PATH = "application.properties";

    public static String getProperty(String propertyName) {
        Properties requestProperties = new Properties();
        try {
            InputStream inputStream = CLASS_LOADER.getResourceAsStream(PROPERTIES_PATH);
            requestProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestProperties.getProperty(propertyName);
    }
}