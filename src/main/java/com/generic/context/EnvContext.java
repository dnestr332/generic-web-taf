package com.generic.context;

import com.generic.config.ConfigReader;
import com.generic.enums.TestEnvironment;
import com.generic.utils.EnumUtils;

public final class EnvContext {

    private static final TestEnvironment ENV = resolve();

    private EnvContext() {}

    private static TestEnvironment resolve() {
        String raw = ConfigReader.getProperty("env");
        if (raw == null || raw.isBlank()) raw = "STAGING"; // TODO : change to the actual default env of your project
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