package com.generic.pages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppPage {

    LOGIN("login"),
    LANDING("home");

    private final String path;
}