package com.propio.resolvers;

import com.propio.pages.PageElement;
import com.propio.pages.PropioPage;
import com.propio.pages.landing.LandingElement;
import com.propio.pages.login.LoginElement;
import com.propio.utils.EnumUtils;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
@ScenarioScope
public class ElementResolver {

    private final Map<PropioPage, Function<String, PageElement>> parsers = Map.of(
            PropioPage.LOGIN, raw -> EnumUtils.parse(LoginElement.class, raw),
            PropioPage.LANDING, raw -> EnumUtils.parse(LandingElement.class, raw)
    );

    public PageElement resolveElement(PropioPage page, String raw) {
        Function<String, PageElement> parser = parsers.get(page);
        if (parser == null) throw new IllegalArgumentException("Unsupported page: " + page);
        return parser.apply(raw);
    }
}