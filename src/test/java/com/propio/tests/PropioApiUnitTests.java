package com.propio.tests;

import com.propio.config.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@Slf4j
@ContextConfiguration(classes = TestConfig.class)
public class PropioApiUnitTests extends AbstractTestNGSpringContextTests {

}