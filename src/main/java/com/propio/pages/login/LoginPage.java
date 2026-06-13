package com.propio.pages.login;

import com.microsoft.playwright.Page;
import com.propio.actions.ElementActions;
import com.propio.pages.BasePage;
import com.propio.pages.PageElement;
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