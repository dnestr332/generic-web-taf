package com.propio.utils;

import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;

import java.time.Duration;
import java.util.function.BooleanSupplier;

@Slf4j
public final class WaitUtils {

    private WaitUtils() {}

    private static final long DEFAULT_TIMEOUT = 10;
    private static final long DEFAULT_POLLING = 200;

    public static void waitUntil(BooleanSupplier condition) {
        waitUntil(condition, DEFAULT_TIMEOUT, DEFAULT_POLLING, "Condition not met");
    }

    public static void waitUntil(BooleanSupplier condition, long seconds, String message) {
        waitUntil(condition, seconds, DEFAULT_POLLING, message);
    }

    public static void waitUntil(BooleanSupplier condition, long seconds, long pollingMillis, String message) {
        Awaitility.await()
                .pollInterval(Duration.ofMillis(pollingMillis))
                .atMost(Duration.ofSeconds(seconds))
                .until(() -> {
                    boolean result = condition.getAsBoolean();
                    if (!result) log.debug("Waiting: {}", message);
                    return result;
                });
    }

    public static void waitUntilIgnoringExceptions(BooleanSupplier condition, long seconds, String message) {
        Awaitility.await()
                .ignoreExceptions()
                .pollInterval(Duration.ofMillis(DEFAULT_POLLING))
                .atMost(Duration.ofSeconds(seconds))
                .until(condition::getAsBoolean);
    }

    public static void sleepSeconds(long seconds) {
        sleepMillis(seconds * 1000);
    }

    public static void sleepMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Sleep interrupted", e);
        }
    }
}