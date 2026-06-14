package com.generic.config;

import com.generic.utils.PropReaderUtils;

import java.util.Properties;

public final class TestDataReader {

    private TestDataReader() {}

    private static final Properties PROPS = PropReaderUtils.load("test-data.properties");

    public static String getProperty(String key) {
        return PropReaderUtils.getProperty(PROPS, key);
    }
}
