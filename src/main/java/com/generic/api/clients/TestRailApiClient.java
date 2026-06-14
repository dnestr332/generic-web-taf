package com.generic.api.clients;

import com.generic.config.EnvConfig;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestRailApiClient extends BaseApiClient {

    @Override
    protected String getUrl() {
        return EnvConfig.get("TESTRAIL_BASE_URL");
    }

    @Override
    protected String getPath() {
        return "";
    }

    public RequestSpecification getSpecification() {
        String user = EnvConfig.get("TESTRAIL_USER");
        String apiKey = EnvConfig.get("TESTRAIL_API_KEY");
        return withBasic(user, apiKey);
    }
}