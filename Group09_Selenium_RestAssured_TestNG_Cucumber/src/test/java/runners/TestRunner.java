package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import utils.Config;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepdefinitions"},
        tags = "@ui",
        plugin = {
                "html:target/cucumber-reports/report.html", // HTML report
        }
)

public class TestRunner extends AbstractTestNGCucumberTests {
        public TestRunner() {
        }

        @BeforeClass
        public void setUp() {
                Config.configureRestAssured();
        }
}
