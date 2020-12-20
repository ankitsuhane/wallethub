package com.wallethub.web.stepdefinitions;

import org.springframework.test.context.ContextConfiguration;
import cucumber.api.java8.En;
import com.wallethub.web.AppConfig;

/**
 * This class bootstraps the Spring Config for the test to run.
 *
 */
@ContextConfiguration(classes = {AppConfig.class})
public class StepsBase implements En {
}