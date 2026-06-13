package com.propio.flows;

import com.propio.pages.login.LoginPage;
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

    public void loginAs(String user) {

    }
}