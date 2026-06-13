package com.propio.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.propio.actions.ElementActions;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BasePage {

    protected final Page page;
    protected final ElementActions elementActions;

    protected abstract Class<? extends PageElement> supportedElement();

    protected Locator byRole(AriaRole role, String name) {
        return page.getByRole(role, new Page.GetByRoleOptions().setName(name));
    }

    protected Locator byRole(AriaRole role, String name, boolean exact) {
        return page.getByRole(role, new Page.GetByRoleOptions()
                .setName(name)
                .setExact(exact));
    }

    protected Locator byText(String visibleText) {
        return page.getByText(visibleText);
    }

    public Locator locator(PageElement element) {
        return byRole(element.getRole(), element.getLabel());
    }

    public void click(PageElement button) {
        validate(button);
        Locator locator = locator(button);
        elementActions.click(locator);
    }

    public void type(PageElement field, String text) {
        validate(field);
        Locator locator = locator(field);
        elementActions.typeWithKeyboard(locator, text);
    }

    private void validate(PageElement element) {
        if (!supportedElement().isInstance(element)) {
            throw new IllegalArgumentException(
                    "Element " + element + " is not supported on " + getClass().getSimpleName()
            );
        }
    }
}