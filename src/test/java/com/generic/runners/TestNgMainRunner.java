package com.generic.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.generic.steps",
                "com.generic.steps.hooks",
        },
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "rerun:target/rerun.txt"
        },
        dryRun = false,
        tags = "@wip and not @ignore"
)
public class TestNgMainRunner extends AbstractTestNGCucumberTests {
}