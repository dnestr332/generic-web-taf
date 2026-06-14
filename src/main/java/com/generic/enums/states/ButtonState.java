package com.generic.enums.states;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ButtonState {

    ENABLED(true),
    DISABLED(false);

    private final boolean enabled;
}
