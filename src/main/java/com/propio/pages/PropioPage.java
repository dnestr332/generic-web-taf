package com.propio.pages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PropioPage {

    LOGIN("login");

    private final String path;
}
