package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import utils.Config;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions"
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @BeforeClass
    public void setUp() {
        Config.configureRestAssured();
    }
}

