package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.APIConfig;
import utils.TestDataCreator;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepdefinitions"},
        tags = "@api1",
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
                TestDataCreator.createInitialData();
        }

        @AfterClass
        public void tearDown() {
        }
}
