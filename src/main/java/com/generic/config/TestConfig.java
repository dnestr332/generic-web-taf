package com.generic.config;

import com.microsoft.playwright.*;
import com.generic.enums.TestBrowser;
import io.cucumber.spring.ScenarioScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.generic.actions",
        "com.generic.api",
        "com.generic.context",
        "com.generic.flows",
        "com.generic.logs",
        "com.generic.pages",
        "com.generic.resolvers",
        "com.generic.service",
})
public class TestConfig {
// TODO : uncomment once credentials for DB are added to .env

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        String url = EnvConfig.get("DB_URL");
//        String user = EnvConfig.get("DB_USER");
//        String password = EnvConfig.get("DB_PASSWORD");
//
//        dataSource.setUrl(url);
//        dataSource.setUsername(user);
//        dataSource.setPassword(password);
//
//        return dataSource;
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }

    @Bean(destroyMethod = "")
    public Playwright playwright() {
        return Playwright.create();
    }

    @Bean(destroyMethod = "")
    public Browser browser(Playwright playwright) {
        TestBrowser selectedBrowser = BrowserConfig.getBrowser();
        boolean isHeadless = BrowserConfig.isHeadless();

        BrowserType browserType = switch (selectedBrowser) {
            case CHROME, EDGE -> playwright.chromium();
            case FIREFOX -> playwright.firefox();
            case SAFARI -> playwright.webkit();
        };
        return browserType.launch(new BrowserType
                .LaunchOptions()
                .setHeadless(isHeadless)
        );
    }

    @Bean(destroyMethod = "")
    @ScenarioScope
    public BrowserContext context(Browser browser) {
        int width = BrowserConfig.getBrowserWidth();
        int height = BrowserConfig.getBrowserHeight();

        return browser.newContext(new Browser
                .NewContextOptions()
                .setViewportSize(width, height)
        );
    }

    @Bean
    @ScenarioScope
    public Page page(BrowserContext context) {
        return context.newPage();
    }
}