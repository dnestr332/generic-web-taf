package com.propio.resolvers;

import com.propio.context.ScenarioContext;
import io.cucumber.spring.ScenarioScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
@RequiredArgsConstructor
public class ContextResolver {

    private final ScenarioContext scenarioContext;
    private final PageResolver pageResolver;
}