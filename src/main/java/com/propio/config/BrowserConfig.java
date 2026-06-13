package com.propio.config;

import com.propio.enums.TestBrowser;
import com.propio.utils.EnumUtils;

public final class BrowserConfig {

    private BrowserConfig() {}

    public static TestBrowser getBrowser() {
        String raw = ConfigReader.getProperty("browser");
        return EnumUtils.parse(TestBrowser.class, raw);
    }

    public static int getBrowserWidth() {
        return Integer.parseInt(ConfigReader.getProperty("browser.width"));
    }

    public static int getBrowserHeight() {
        return Integer.parseInt(ConfigReader.getProperty("browser.height"));
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(
                System.getProperty(
                        "browser.headless",
                        ConfigReader.getProperty("browser.headless")
                )
        );
    }
}