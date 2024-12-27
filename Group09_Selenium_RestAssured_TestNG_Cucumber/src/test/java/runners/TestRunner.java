package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import utils.APIConfig;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepdefinitions"},
        tags = "@api",
        plugin = {
                "html:target/cucumber-reports/report.html", // HTML report
        }
)

public class TestRunner extends AbstractTestNGCucumberTests {
        public TestRunner() {
        }

        @BeforeClass
        public void setUp() {
                APIConfig.configureRestAssured();
        }
}
