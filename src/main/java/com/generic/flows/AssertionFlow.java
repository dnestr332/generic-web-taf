package com.generic.flows;

import com.microsoft.playwright.Locator;
import com.generic.actions.ElementActions;
import com.generic.assertions.Hardly;
import com.generic.assertions.Softly;
import com.generic.enums.states.*;
import com.generic.pages.BasePage;
import com.generic.pages.PageElement;
import com.generic.pages.AppPage;
import com.generic.resolvers.PageResolver;
import io.cucumber.spring.ScenarioScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

@Component
@ScenarioScope
@RequiredArgsConstructor
public class AssertionFlow {

    private final PageResolver pageResolver;
    private final ElementActions elementActions;

    protected BasePage resolve(AppPage page) {
        return pageResolver.resolvePage(page);
    }

    private void assertBool(AssertionState state, BooleanSupplier condition, String message) {
        switch (state) {
            case STRICTLY -> Hardly.isTrue(condition.getAsBoolean(), message);
            case SOFTLY -> Softly.isTrue(condition.getAsBoolean(), message);
        }
    }

    private <T> void assertEquals(AssertionState state, Supplier<T> actual, T expected, String message) {
        T value = actual.get();

        switch (state) {
            case STRICTLY -> Hardly.isEqual(value, expected, message);
            case SOFTLY -> Softly.isEqual(value, expected, message);
        }
    }

    public void verifyVisibleState(AppPage page, PageElement element, AssertionState assertion, VisibleState visible) {
        Locator locator = resolve(page).locator(element);

        BooleanSupplier condition = switch (visible) {
            case VISIBLE -> () -> locator.count() > 0 && locator.first().isVisible();
            case NOT_VISIBLE -> () -> locator.count() == 0 || !locator.first().isVisible();
        };

        String msg = "%s should be %s".formatted(element, visible.name().replace("_", " ").toLowerCase());
        assertBool(assertion, condition, msg);
    }

    public void verifyButtonState(AppPage page, PageElement element, AssertionState assertion, ButtonState state) {
        Locator locator = resolve(page).locator(element);

        BooleanSupplier condition = switch (state) {
            case ENABLED -> locator::isEnabled;
            case DISABLED -> locator::isDisabled;
        };

        String msg = "%s should be %s".formatted(element, state);
        assertBool(assertion, condition, msg);
    }

    public void verifyFieldState(AppPage page, PageElement element, AssertionState assertion, FieldState state) {
        Locator locator = resolve(page).locator(element);
        BooleanSupplier condition = switch (state) {
            case EDITABLE -> locator::isEditable;
            case READ_ONLY -> () -> locator.isDisabled() || !locator.isEditable();
        };

        String msg = "%s should be %s".formatted(element, state.name().replace("_", " ").toLowerCase());
        assertBool(assertion, condition, msg);
    }

    public void verifyToggleState(AppPage page, PageElement element, AssertionState assertion, ToggleState state) {
        Locator locator = resolve(page).locator(element);
        BooleanSupplier condition = switch (state) {
            case CHECKED -> locator::isChecked;
            case UNCHECKED -> () -> !locator.isChecked();
        };

        String msg = "Toggle should be " + state.name().toLowerCase();
        assertBool(assertion, condition, msg);
    }

    public void verifyTextEquals(AppPage page, PageElement element, AssertionState assertion, String expected) {
        Locator locator = resolve(page).locator(element);
        Supplier<String> actual = () -> elementActions
                .text(locator)
                .replace("’", "'");

        String msg = "%s text should be <%s>".formatted(element, expected);
        assertEquals(assertion, actual, expected, msg);
    }

    public <T> void verifyEquals(AssertionState assertion, Supplier<T> actual, T expected, String context) {
        assertEquals(assertion, actual, expected, context);
    }

    public void verifyTextContains(AppPage page, PageElement element, AssertionState assertion, String partial) {
        Locator locator = resolve(page).locator(element);
        BooleanSupplier condition = () -> elementActions
                .text(locator)
                .contains(partial);

        String msg = "%s should contain <%s>".formatted(element, partial);
        assertBool(assertion, condition, msg);
    }
}