package com.generic.service;

import com.generic.api.clients.TestRailApiClient;
import com.generic.config.ConfigReader;
import com.generic.context.TestFailureContext;
import com.generic.enums.TestRailStatus;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Throwables;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestRailService {

    private final TestRailApiClient client;

    public void pushResult(Scenario scenario) {
        long runId = resolveRunId(scenario);
        Long caseId = getCaseId(scenario);
        if (caseId == null) return;

        TestRailStatus status = scenario.isFailed()
                ? TestRailStatus.FAILED
                : TestRailStatus.PASSED;

        String comment = buildComment(scenario);
        addResult(runId, caseId, status, comment);
    }

    private void addResult(long runId, long caseId, TestRailStatus status, String comment) {
        String endpoint = String.format(
                "/index.php?/api/v2/add_result_for_case/%d/%d",
                runId, caseId
        );

        Response response = client.getSpecification()
                .body(Map.of(
                        "status_id", status.getId(),
                        "comment", comment
                ))
                .post(endpoint);

        if (response.statusCode() >= 300) {
            String responseBody = response.asString();
            if (responseBody != null && responseBody.contains("The test run is already completed")) {
                log.warn("TestRail result not added: Run ID {} is already completed/closed.", runId);
                return;
            }
            throw new IllegalStateException("TestRail API failed: " + responseBody);
        }
    }

    private long resolveRunId(Scenario scenario) {
        Set<String> tags = scenario.getSourceTagNames()
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        // TODO : change to the actual tag name of your project
        if (tags.contains("@comName")) return parseRunId("testrail.runId");

        throw new IllegalStateException(
                "❌ No project tag (@comName, @jopa, etc...) found for scenario: " + scenario.getName()
        );
    }

    private long parseRunId(String propertyKey) {
        String runIdValue = ConfigReader.getProperty(propertyKey);
        try {
            return Long.parseLong(runIdValue);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    "❌ Invalid TestRail run id configured for property '" + propertyKey + "': " + runIdValue,
                    e
            );
        }
    }

    private Long getCaseId(Scenario scenario) {
        return scenario.getSourceTagNames()
                .stream()
                .filter(tag -> tag.matches("@C\\d+"))
                .findFirst()
                .map(tag -> Long.parseLong(tag.substring(2)))
                .orElse(null);
    }

    private String buildComment(Scenario scenario) {
        String base = scenario.isFailed()
                ? "Automated test FAILED: "
                : "Automated test PASSED: ";

        StringBuilder sb = new StringBuilder(base).append(scenario.getName());

        if (!scenario.isFailed()) return sb.toString();
        Throwable error = TestFailureContext.getError();
        if (error == null) return sb.append("\n\n(No exception captured)").toString();

        Throwables.removeAssertJRelatedElementsFromStackTrace(error);
        Throwable root = Throwables.getRootCause(error);
        if (root == null) root = error;
        sb.append("\n\nException: ").append(root.getClass().getSimpleName());

        String message = root.getMessage();
        if (message != null && !message.isBlank()) {
            sb.append("\n\nDetails: ").append(message.split("\n", 2)[0]);
        }
        return sb.toString();
    }
}