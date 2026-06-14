package com.generic.pages.landing;

import com.microsoft.playwright.Page;
import com.generic.actions.ElementActions;
import com.generic.config.TestDataReader;
import com.generic.enums.states.VisibleState;
import com.generic.pages.BasePage;
import com.generic.pages.PageElement;
import com.generic.pages.AppPage;
import com.generic.utils.WaitUtils;
import io.cucumber.spring.ScenarioScope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ScenarioScope
public class LandingPage extends BasePage {

    public LandingPage(Page page, ElementActions elementActions) {
        super(page, elementActions);
    }

    @Override
    protected Class<? extends PageElement> supportedElement() {
        return LandingElement.class;
    }

    public void navigateTo(AppPage targetPage) {
        navigateTo(targetPage.getPath());
    }

    public void navigateTo(String path) {
        String url = TestDataReader.getProperty("base.url") + "/" + path;
        log.info("Navigating to the URL: {}", url);
        page.navigate(url);
        elementActions.waitForStability();
    }

    public boolean isMessageVisible(String text, VisibleState state) {
        return switch (state) {
            case VISIBLE -> byText(text).isVisible();
            case NOT_VISIBLE -> {
                WaitUtils.sleepSeconds(3);
                yield byText(text).isHidden();
            }
        };
    }
}