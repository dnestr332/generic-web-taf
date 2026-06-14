package com.generic.flows;

import com.generic.pages.login.LoginPage;
import io.cucumber.spring.ScenarioScope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ScenarioScope
@RequiredArgsConstructor
public class LoginFlow {

    private final LoginPage loginPage;

    // TODO : implement login method for your project
    public void loginAs(String user) {

    }
}