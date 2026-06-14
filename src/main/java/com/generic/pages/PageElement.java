package com.generic.pages;

import com.microsoft.playwright.options.AriaRole;

public interface PageElement {

    String getLabel();
    AriaRole getRole();
}
