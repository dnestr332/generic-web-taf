package com.generic.steps.hooks;

import com.generic.config.TestConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = {TestConfig.class})
public class CucumberSpringConfig {
}
