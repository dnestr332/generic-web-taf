package com.propio.config;

import com.propio.context.EnvContext;
import io.github.cdimascio.dotenv.Dotenv;

public final class EnvConfig {

    private EnvConfig() {}

    private static final Dotenv DOTENV = Dotenv.configure()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    public static String get(String key) {
        return read(key);
    }

    private static String readByEnv(String baseKey) {
        String envName = EnvContext.getEnv().name();
        return read(baseKey + "_" + envName);
    }

    private static String read(String key) {
        String value = System.getProperty(key);
        if (value != null && !value.isBlank()) {
            return value;
        }
        value = System.getenv(key);
        if (value != null && !value.isBlank()) {
            return value;
        }
        value = DOTENV.get(key);
        if (value != null && !value.isBlank()) {
            return value;
        }

        throw new IllegalStateException("Missing required property: " + key);
    }
}