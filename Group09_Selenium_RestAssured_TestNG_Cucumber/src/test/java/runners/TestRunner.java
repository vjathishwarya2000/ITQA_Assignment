package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import utils.APIConfig;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepdefinitions"},
        tags = "@api3",
        plugin = {
                "pretty", // For console output
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", // Allure plugin
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
