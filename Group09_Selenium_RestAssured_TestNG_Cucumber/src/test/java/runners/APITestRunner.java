package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.APIConfig;
import utils.TestDataCreator;

@CucumberOptions(
        features = {"src/test/resources/features"}, // Path to your feature files
        glue = {"stepdefinitions.api"}, // Path to API step definitions
        tags = "@api", // Only run tests with @api tag
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        }
)
public class APITestRunner extends AbstractTestNGCucumberTests {
        @BeforeClass
        public void setUp() {
                System.out.println("Setting up API tests...");
                APIConfig.configureRestAssured();
                TestDataCreator.createInitialData();
        }

        @AfterClass
        public void tearDown() {
                System.out.println("Tearing down API tests...");
        }
}
