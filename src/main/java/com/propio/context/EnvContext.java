package com.propio.context;

import com.propio.config.ConfigReader;
import com.propio.enums.TestEnvironment;
import com.propio.utils.EnumUtils;

public final class EnvContext {

    private static final TestEnvironment ENV = resolve();

    private EnvContext() {}

    private static TestEnvironment resolve() {
        String raw = ConfigReader.getProperty("env");
        if (raw == null || raw.isBlank()) raw = "STAGING";
        return EnumUtils.parse(TestEnvironment.class, raw);
    }

    public static TestEnvironment getEnv() {
        return ENV;
    }

    public static boolean isCi() {
        return Boolean.parseBoolean(
                System.getProperty("CI", ConfigReader.getProperty("CI"))
        );
    }
}