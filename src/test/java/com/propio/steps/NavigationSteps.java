package com.propio.steps;

import com.propio.pages.PropioPage;
import com.propio.pages.landing.LandingPage;
import com.propio.resolvers.ContextResolver;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NavigationSteps {

    private final LandingPage landingPage;
    private final ContextResolver contextResolver;

    @When("user navigates to the {pageName} page")
    public void user_navigates_to_the_page(PropioPage pageName) {
        landingPage.navigateTo(pageName);
    }

    @When("user navigates to the {string} path")
    public void userNavigatesToPath(String path) {
        path = contextResolver.resolvePath(path);
        landingPage.navigateTo(path);
    }
}