package com.propio.pages.login;

import com.microsoft.playwright.options.AriaRole;
import com.propio.pages.PageElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginElement implements PageElement {

    EMAIL_FIELD("Email", AriaRole.TEXTBOX),
    PASSWORD_FIELD("Password", AriaRole.TEXTBOX),
    LOGIN_BUTTON("Login", AriaRole.BUTTON);

    private final String label;
    private final AriaRole role;
}
