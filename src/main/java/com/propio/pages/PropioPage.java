package com.propio.pages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PropioPage {

    LOGIN("login"),
    LANDING("home");

    private final String path;
}