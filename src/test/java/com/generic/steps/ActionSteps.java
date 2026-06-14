package com.generic.steps;

import com.generic.flows.ActionFlow;
import com.generic.pages.PageElement;
import com.generic.pages.AppPage;
import com.generic.resolvers.ContextResolver;
import com.generic.resolvers.ElementResolver;
import io.cucumber.java.en.And;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActionSteps {

    private final ActionFlow actionFlow;
    private final ElementResolver itemResolver;
    private final ContextResolver contextResolver;

    @And("user clicks {string} button on {pageName} page")
    public void userClicks(String rawItem, AppPage page) {
        PageElement element = itemResolver.resolveElement(page, rawItem);
        actionFlow.click(element, page);
    }

    @And("user fills {string} field on {pageName} page as {string}")
    public void userTypes(String rawItem, AppPage page, String value) {
        PageElement element = itemResolver.resolveElement(page, rawItem);
        value = contextResolver.resolveInput(element, value);
        actionFlow.type(element, page, value);
    }

    @And("user opens {string} dropdown on {pageName} page")
    public void userOpensDropdown(String item, AppPage page) {
        userClicks(item, page);
    }

    @And("user selects {string} option on {pageName} page")
    public void userSelectOption(String item, AppPage page) {
        userClicks(item, page);
    }
}