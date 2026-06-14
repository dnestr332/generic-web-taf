package com.generic.resolvers;

import com.generic.context.ScenarioContext;
import com.generic.pages.PageElement;
import io.cucumber.spring.ScenarioScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
@RequiredArgsConstructor
public class ContextResolver {

    private final ScenarioContext scenarioContext;
    private final PageResolver pageResolver;

    public String resolveExpectedValue(PageElement element, String value) {
        return value;
    }

    public int resolveCountValue(String value) {
        return Integer.parseInt(value);
    }

    public String resolveInput(PageElement element, String value) {
        return value;
    }

    public String resolvePath(String path) {
        return path;
    }
}