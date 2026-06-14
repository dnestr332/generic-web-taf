package com.generic.steps.hooks;

import com.generic.enums.states.*;
import com.generic.pages.AppPage;
import com.generic.utils.EnumUtils;
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
    public AppPage pageName(String value) {
        return EnumUtils.parse(AppPage.class, value);
    }
}