package com.generic.flows;

import com.generic.pages.BasePage;
import com.generic.pages.PageElement;
import com.generic.pages.AppPage;
import com.generic.resolvers.PageResolver;
import io.cucumber.spring.ScenarioScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
@RequiredArgsConstructor
public class ActionFlow {

    private final PageResolver pageResolver;

    protected BasePage resolve(AppPage page) {
        return pageResolver.resolvePage(page);
    }

    public void click(PageElement element, AppPage page) {
        resolve(page).click(element);
    }

    public void type(PageElement element, AppPage page, String value) {
        resolve(page).type(element, value);
    }
}