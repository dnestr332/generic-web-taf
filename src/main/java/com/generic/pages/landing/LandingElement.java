package com.generic.pages.landing;

import com.microsoft.playwright.options.AriaRole;
import com.generic.pages.PageElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LandingElement implements PageElement {

    ;

    private final String label;
    private final AriaRole role;
}