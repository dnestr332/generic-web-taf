package com.propio.steps;

import com.propio.flows.LoginFlow;
import com.propio.pages.login.LoginPage;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginSteps {

    private final LoginFlow loginFlow;

    @When("{string} is logged in to the App")
    public void isLoggedInToTheApp(String userType) {
        loginFlow.loginAs(userType);
    }
}