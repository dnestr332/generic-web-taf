package com.propio.config;

import com.microsoft.playwright.*;
import com.propio.enums.TestBrowser;
import io.cucumber.spring.ScenarioScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {
        "com.propio.actions",
        "com.propio.api",
        "com.propio.context",
        "com.propio.flows",
        "com.propio.logs",
        "com.propio.pages",
        "com.propio.resolvers",
        "com.propio.service",
})
public class TestConfig {

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