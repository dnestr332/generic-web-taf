package com.propio.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SystemSteps {

    private final Page page;

    @When("system waits for {int} seconds")
    public void system_waits_for_seconds(long sec) {
        log.info("Waiting for {} seconds", sec);
        page.waitForTimeout(sec * 1000);
    }

    @When("page is refreshed {int} times")
    public void pageIsRefreshedTimes(int limit) {
        for(int i = 0; i < limit; i++) {
            page.reload();
            page.waitForLoadState(LoadState.NETWORKIDLE);
            log.info("Page is refreshed {} time(s)", i + 1);
        }
    }
}