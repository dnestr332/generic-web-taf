package com.propio.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TestRailStatus {

    PASSED(1),
    BLOCKED(2),
    RETEST(4),
    FAILED(5);

    private final int id;
}
