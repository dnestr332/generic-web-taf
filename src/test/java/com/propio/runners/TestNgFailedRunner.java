package com.propio.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "target/rerun.txt",
        glue = {
                "com.propio.steps",
                "com.propio.steps.hooks",
        },
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class TestNgFailedRunner extends AbstractTestNGCucumberTests {
}
