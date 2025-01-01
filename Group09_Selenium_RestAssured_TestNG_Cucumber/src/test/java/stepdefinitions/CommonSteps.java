package stepdefinitions;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import pages.IceHRMHomePage;

public class CommonSteps {
    WebDriver driver;
    IceHRMHomePage homePage;

    public CommonSteps(WebDriver driver) {
        this.driver = driver;
        this.homePage = new IceHRMHomePage(driver);
    }

    @And("I click the login button")
    public void i_click_the_login_button() {
        homePage.clickLogin();
    }
    @Then("I should be on the dashboard")
    public void i_should_be_on_the_dashboard() {
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://icehrmpro.gamonoid.com/?g=admin&n=dashboard&m=admin_Admin";
        if (!actualUrl.equals(expectedUrl)) {
            throw new AssertionError("URL does not match! Expected: '" + expectedUrl + "', Found: '" + actualUrl + "'");
        }
    }
}
