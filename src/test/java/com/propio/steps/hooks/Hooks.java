package com.propio.steps.hooks;

import com.microsoft.playwright.Page;
import com.propio.assertions.Softly;
import com.propio.config.BrowserConfig;
import com.propio.context.EnvContext;
import com.propio.context.TestFailureContext;
import com.propio.service.TestRailService;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertionError;

import java.io.ByteArrayInputStream;

import static com.propio.logs.LogStyles.*;

@Slf4j
@RequiredArgsConstructor
public class Hooks {

    private final Page page;
    private final TestRailService testRailService;

    @BeforeAll
    public static void annotate() {
        String run = EnvContext.isCi() ? "CI" : "LOCAL";
        String env = EnvContext.getEnv().toString();
        log.info("""

        ============================================================================
         ██████╗ ██████╗  ██████╗ ██████╗ ██╗ ██████╗     ████████╗ █████╗ ███████╗
         ██╔══██╗██╔══██╗██╔═══██╗██╔══██╗██║██╔═══██╗    ╚══██╔══╝██╔══██╗██╔════╝
         ██████╔╝██████╔╝██║   ██║██████╔╝██║██║   ██║       ██║   ███████║█████╗
         ██╔═══╝ ██╔══██╗██║   ██║██╔═══╝ ██║██║   ██║       ██║   ██╔══██║██╔══╝
         ██║     ██║  ██║╚██████╔╝██║     ██║╚██████╔╝       ██║   ██║  ██║██║
         ╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝ ╚═════╝        ╚═╝   ╚═╝  ╚═╝╚═╝

                         P R O P I O   T E S T   A U T O M A T I O N

         Environment : %s
         Browser     : %s
         Headless    : %s
         Run         : %s
        ============================================================================
        """.formatted(
                env,
                BrowserConfig.getBrowser(),
                BrowserConfig.isHeadless(),
                run
        ));
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] screenshot = page.screenshot();
                Allure.addAttachment(
                        "Screenshot", "image/png",
                        new ByteArrayInputStream(screenshot), ".png"
                );
            }
            log.info("{} {} Sending results to TestRail...", INFO_SHORT, GHOST);
            testRailService.pushResult(scenario);
        } catch (Exception e) {
            log.error("Error in teardown hook", e);
        }
    }

    @After
    public void softAssert() {
        try {
            Softly.assertAll();
        } catch (SoftAssertionError e) {
            TestFailureContext.setError(e);
            throw e;
        }
    }
}