package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.APIConfig;
import utils.TestDataCreator;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepdefinitions.api"},
        tags = "@api",
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        }
)
public class APITestRunner extends AbstractTestNGCucumberTests {
        @BeforeClass
        public void setUp() {
                APIConfig.configureRestAssured();
                TestDataCreator.createInitialData();
        }

        @AfterClass
        public void tearDown() {
                System.out.println("Tearing down API tests...");
        }
}
