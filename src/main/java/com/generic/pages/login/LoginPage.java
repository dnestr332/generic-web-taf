package com.generic.pages.login;

import com.microsoft.playwright.Page;
import com.generic.actions.ElementActions;
import com.generic.pages.BasePage;
import com.generic.pages.PageElement;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
public class LoginPage extends BasePage {

    public LoginPage(Page page, ElementActions elementActions) {
        super(page, elementActions);
    }

    @Override
    protected Class<? extends PageElement> supportedElement() {
        return LoginElement.class;
    }
}