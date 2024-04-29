package com.loqbox.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.loqbox.steps", "com.loqbox.hooks"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        tags = "@SignUp_NegativeScenario"
)

public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
}
