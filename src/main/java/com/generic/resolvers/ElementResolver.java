package com.generic.resolvers;

import com.generic.pages.PageElement;
import com.generic.pages.AppPage;
import com.generic.pages.landing.LandingElement;
import com.generic.pages.login.LoginElement;
import com.generic.utils.EnumUtils;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
@ScenarioScope
public class ElementResolver {

    private final Map<AppPage, Function<String, PageElement>> parsers = Map.of(
            AppPage.LOGIN, raw -> EnumUtils.parse(LoginElement.class, raw),
            AppPage.LANDING, raw -> EnumUtils.parse(LandingElement.class, raw)
    );

    public PageElement resolveElement(AppPage page, String raw) {
        Function<String, PageElement> parser = parsers.get(page);
        if (parser == null) throw new IllegalArgumentException("Unsupported page: " + page);
        return parser.apply(raw);
    }
}