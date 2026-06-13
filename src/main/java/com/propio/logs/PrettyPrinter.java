package com.propio.logs;

import com.microsoft.playwright.Locator;
import com.propio.actions.ElementAction;
import io.cucumber.spring.ScenarioScope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.propio.logs.LogStyles.*;

@Slf4j
@Component
@ScenarioScope
public class PrettyPrinter {

    public void start(ElementAction action, Locator locator, String details) {
        String prefix = BLUE + CLICK + RESET + " ";
        String shortTag = INFO_SHORT;

        String actionLabel = (details == null)
                ? action.name()
                : action.name() + " " + details;

        if (locator == null) {
            log.info("{} {} {}{}{}",
                    prefix,
                    shortTag,
                    GREEN,
                    actionLabel,
                    RESET
            );
            return;
        }

        log.info("{} {} {}{}{}  [{}] {}",
                prefix,
                shortTag,
                GREEN,
                actionLabel,
                RESET,
                getLocatorType(locator),
                getLocatorValue(locator)
        );
    }

    public void ok(double sec) {
        log.info("   {} {} {} ({} sec)",
                OK_SHORT,
                GREEN + OK + RESET,
                GREEN + "OK" + RESET,
                sec
        );
    }

    public void fail(Throwable t) {
        String line = (t.getMessage() == null)
                ? "<no message>"
                : t.getMessage().split("\\R", 2)[0];

        log.error("   {} {} {}",
                FAIL_SHORT,
                RED + FAIL + RESET,
                line
        );

        String locatorLine = extractLocatorFromMessage(line);
        if (locatorLine != null) {
            log.error("     {}{}{}", RED, locatorLine, RESET);
        }

        StackTraceElement[] st = t.getStackTrace();
        for (int i = 0; i < Math.min(3, st.length); i++) {
            log.error("     {}{}{}", RED, st[i], RESET);
        }
    }

    private String extractLocatorFromMessage(String message) {
        int idx = message.indexOf("locator(");
        if (idx > -1) return message.substring(idx).trim();

        idx = message.indexOf("getBy");
        if (idx > -1) return message.substring(idx).trim();

        return null;
    }

    private String getLocatorType(Locator locator) {
        String s = locator.toString();
        int separator = s.indexOf(": ");
        return (separator > -1)
                ? s.substring(0, separator)
                : s;
    }

    private String getLocatorValue(Locator locator) {
        String s = locator.toString();
        int separator = s.indexOf(": ");
        return (separator > -1)
                ? s.substring(separator + 2)
                : s;
    }
}