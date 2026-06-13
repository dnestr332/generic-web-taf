package com.propio.steps;

import com.propio.assertions.Softly;
import com.propio.context.ScenarioContext;
import com.propio.enums.states.AssertionState;
import com.propio.enums.states.ButtonState;
import com.propio.enums.states.FieldState;
import com.propio.enums.states.VisibleState;
import com.propio.flows.AssertionFlow;
import com.propio.pages.PageElement;
import com.propio.pages.PropioPage;
import com.propio.pages.landing.LandingPage;
import com.propio.resolvers.ContextResolver;
import com.propio.resolvers.ElementResolver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;

import static com.propio.context.Context.COUNT;

@RequiredArgsConstructor
public class AssertionSteps {

    private final ScenarioContext scenarioContext;
    private final ContextResolver contextResolver;
    private final ElementResolver elementResolver;
    private final AssertionFlow assertionFlow;
    private final LandingPage landingPage;

    @Then("the {string} item on {pageName} page is {assertionState} {visibleState}")
    public void verifyVisibility(String rawItem, PropioPage page, AssertionState assertion, VisibleState visible) {
        PageElement element = elementResolver.resolveElement(page, rawItem);
        assertionFlow.verifyVisibleState(page, element, assertion, visible);
    }

    @Then("the {string} button on {pageName} page is {assertionState} {buttonState}")
    public void verifyEnabled(String rawItem, PropioPage page, AssertionState assertion, ButtonState state) {
        PageElement element = elementResolver.resolveElement(page, rawItem);
        assertionFlow.verifyButtonState(page, element, assertion, state);
    }

    @Then("the {string} field on {pageName} page is {assertionState} {fieldState}")
    public void fieldIsReadOnly(String rawField, PropioPage page, AssertionState assertion, FieldState fieldState) {
        PageElement element = elementResolver.resolveElement(page, rawField);
        assertionFlow.verifyFieldState(page, element, assertion, fieldState);
    }

    @Then("the text of {string} item on {pageName} page is {assertionState} equals {string}")
    public void verifyTextEquals(String rawItem, PropioPage page, AssertionState assertion, String expected) {
        PageElement element = elementResolver.resolveElement(page, rawItem);
        expected = contextResolver.resolveExpectedValue(element, expected);
        assertionFlow.verifyTextEquals(page, element, assertion, expected);
    }

    @Then("the text of {string} item on {pageName} page is {assertionState} contains {string}")
    public void validateFieldValueContains(String rawItem, PropioPage page, AssertionState assertion, String partialValue) {
        PageElement element = elementResolver.resolveElement(page, rawItem);
        partialValue = contextResolver.resolveExpectedValue(element, partialValue);
        assertionFlow.verifyTextContains(page, element, assertion, partialValue);
    }

    @And("user saves the count of {string} field")
    public void userSavesTheCountOfFieldOnPassengersPage(String field) {
        contextResolver.resolveCountValue(field);
    }

    @And("the count of {string} field should change by {int}")
    public void theCountOfFieldIsSetTo(String field, int delta) {
        int initial = scenarioContext.get(COUNT);
        int expected = initial + delta;
        int actual = contextResolver.resolveCountValue(field);
        Softly.isEqual(actual, expected,
                "Count should change by " + delta
        );
    }

    @And("the count of {string} field should be {int}")
    public void theCountOfFieldShouldBe(String field, int expected) {
        int actual = contextResolver.resolveCountValue(field);
        Softly.isEqual(actual, expected,
                "Count should be equal"
        );
    }

    @And("the toast message starting with {string} is {visibleState}")
    public void successMessagePopsUpStartingWith(String expectedMessage, VisibleState state) {
        Softly.isTrue(landingPage.isMessageVisible(expectedMessage, state),
                "Toast message should be visible"
        );
    }
}