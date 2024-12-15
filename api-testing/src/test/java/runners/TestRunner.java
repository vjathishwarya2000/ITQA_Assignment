package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = "stepdefinitions",               // Package containing step definitions
        tags = "@filter",                            // (Optional) Specify a tag to filter tests
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        },
        monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {
}
