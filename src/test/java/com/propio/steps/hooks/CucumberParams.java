package com.propio.steps.hooks;

import com.propio.enums.states.*;
import com.propio.pages.PropioPage;
import com.propio.utils.EnumUtils;
import io.cucumber.java.ParameterType;

public class CucumberParams {

    @ParameterType("(?i)STRICTLY|SOFTLY")
    public AssertionState assertionState(String value) {
        return EnumUtils.parse(AssertionState.class, value);
    }

    @ParameterType("(?i)ENABLED|DISABLED")
    public ButtonState buttonState(String value) {
        return EnumUtils.parse(ButtonState.class, value);
    }

    @ParameterType("(?i)VISIBLE|NOT VISIBLE")
    public VisibleState visibleState(String value) {
        return EnumUtils.parse(VisibleState.class, value);
    }

    @ParameterType(value = "(?i)EDITABLE|READ ONLY")
    public FieldState fieldState(String raw) {
        return EnumUtils.parse(FieldState.class, raw);
    }

    @ParameterType(value = "(?i)CHECKED|UNCHECKED")
    public ToggleState toggleState(String raw) {
        return EnumUtils.parse(ToggleState.class, raw);
    }

    @ParameterType(value = "(?i)[a-zA-Z0-9 _-]+")
    public PropioPage pageName(String value) {
        return EnumUtils.parse(PropioPage.class, value);
    }
}