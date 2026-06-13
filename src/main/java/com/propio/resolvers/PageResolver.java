package com.propio.resolvers;

import com.propio.pages.BasePage;
import com.propio.pages.PropioPage;
import com.propio.pages.login.LoginPage;
import io.cucumber.spring.ScenarioScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
@RequiredArgsConstructor
public class PageResolver {

    private final LoginPage loginPage;

    public BasePage resolvePage(PropioPage page) {
        return switch (page) {
            case LOGIN -> loginPage;
            default -> throw new IllegalArgumentException("Page not found: " + page);
        };
    }
}