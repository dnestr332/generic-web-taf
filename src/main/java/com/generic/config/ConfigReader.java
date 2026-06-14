package com.generic.config;

import com.generic.utils.PropReaderUtils;

import java.util.Properties;

public final class ConfigReader {

    private ConfigReader() {}

    private static final Properties PROPS = PropReaderUtils.load("config.properties");

    public static String getProperty(String key) {
        return PropReaderUtils.getProperty(PROPS, key);
    }
}