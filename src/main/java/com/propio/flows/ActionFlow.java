package com.propio.flows;

import com.propio.pages.BasePage;
import com.propio.pages.PageElement;
import com.propio.pages.PropioPage;
import com.propio.resolvers.PageResolver;
import io.cucumber.spring.ScenarioScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
@RequiredArgsConstructor
public class ActionFlow {

    private final PageResolver pageResolver;

    protected BasePage resolve(PropioPage page) {
        return pageResolver.resolvePage(page);
    }

    public void click(PageElement element, PropioPage page) {
        resolve(page).click(element);
    }

    public void type(PageElement element, PropioPage page, String value) {
        resolve(page).type(element, value);
    }
}