package com.wallethub.web.testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/cucumber-reports/Cucumber2.json"},
        features = "src/test/java/com/wallethub/web/features",
        glue = "com.wallethub.web.stepdefinitions",
       // dryRun=true,
        tags = {"@Web"},
        monochrome=true)

public class TestRunner {
}
