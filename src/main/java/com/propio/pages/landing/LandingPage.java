package com.propio.pages.landing;

import com.microsoft.playwright.Page;
import com.propio.actions.ElementActions;
import com.propio.config.TestDataReader;
import com.propio.enums.states.VisibleState;
import com.propio.pages.BasePage;
import com.propio.pages.PageElement;
import com.propio.pages.PropioPage;
import com.propio.utils.WaitUtils;
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

    public void navigateTo(PropioPage targetPage) {
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