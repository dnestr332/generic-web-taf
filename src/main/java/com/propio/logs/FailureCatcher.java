package com.propio.logs;

import com.microsoft.playwright.Locator;
import com.propio.actions.ElementAction;
import com.propio.context.TestFailureContext;
import io.cucumber.spring.ScenarioScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@ScenarioScope
@RequiredArgsConstructor
public class FailureCatcher {

    private final PrettyPrinter prettyPrinter;

    public <R> R withFailureCapture(ElementAction action, Locator locator, String details, Supplier<R> supplier) {
        long start = System.currentTimeMillis();
        prettyPrinter.start(action, locator, details);

        try {
            R result = supplier.get();
            double sec = (System.currentTimeMillis() - start) / 1000.0;
            prettyPrinter.ok(sec);
            return result;
        } catch (Throwable t) {
            prettyPrinter.fail(t);
            TestFailureContext.setError(t);
            throw t;
        }
    }

    public void withFailureCapture(ElementAction action, Locator locator, String details, Runnable runnable) {
        withFailureCapture(action, locator, details, () -> {
            runnable.run();
            return null;
        });
    }

    public <R> R withFailureCapture(ElementAction action, Locator locator, Supplier<R> supplier) {
        return withFailureCapture(action, locator, null, supplier);
    }

    public void withFailureCapture(ElementAction action, Locator locator, Runnable runnable) {
        withFailureCapture(action, locator, null, runnable);
    }

    public void withFailureCapture(ElementAction action, Runnable runnable) {
        withFailureCapture(action, null, null, runnable);
    }
}