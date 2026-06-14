package com.generic.resolvers;

import com.generic.pages.BasePage;
import com.generic.pages.AppPage;
import com.generic.pages.landing.LandingPage;
import com.generic.pages.login.LoginPage;
import io.cucumber.spring.ScenarioScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
@RequiredArgsConstructor
public class PageResolver {

    private final LoginPage loginPage;
    private final LandingPage landingPage;

    public BasePage resolvePage(AppPage page) {
        return switch (page) {
            case LOGIN -> loginPage;
            case LANDING -> landingPage;
            default -> throw new IllegalArgumentException("Page not found: " + page);
        };
    }
}