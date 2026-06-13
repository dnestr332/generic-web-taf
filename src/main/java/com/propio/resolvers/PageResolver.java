package com.propio.resolvers;

import com.propio.pages.BasePage;
import com.propio.pages.PropioPage;
import com.propio.pages.landing.LandingPage;
import com.propio.pages.login.LoginPage;
import io.cucumber.spring.ScenarioScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
@RequiredArgsConstructor
public class PageResolver {

    private final LoginPage loginPage;
    private final LandingPage landingPage;

    public BasePage resolvePage(PropioPage page) {
        return switch (page) {
            case LOGIN -> loginPage;
            case LANDING -> landingPage;
            default -> throw new IllegalArgumentException("Page not found: " + page);
        };
    }
}