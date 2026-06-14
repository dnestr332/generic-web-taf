package com.generic.steps;

import com.generic.pages.AppPage;
import com.generic.pages.landing.LandingPage;
import com.generic.resolvers.ContextResolver;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NavigationSteps {

    private final LandingPage landingPage;
    private final ContextResolver contextResolver;

    @When("user navigates to the {pageName} page")
    public void user_navigates_to_the_page(AppPage pageName) {
        landingPage.navigateTo(pageName);
    }

    @When("user navigates to the {string} path")
    public void userNavigatesToPath(String path) {
        path = contextResolver.resolvePath(path);
        landingPage.navigateTo(path);
    }
}