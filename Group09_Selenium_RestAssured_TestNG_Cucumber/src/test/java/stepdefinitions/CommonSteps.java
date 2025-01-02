package stepdefinitions;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import pages.IceHRMHomePage;
import utils.WebDriverConfig;

import java.io.IOException;

public class CommonSteps {
    protected WebDriver driver;
    protected IceHRMHomePage homePage;

    @Given("I am on the login page for the company dashboard")
    public void i_am_on_the_login_page_for_company_dashboard() throws IOException {
        driver = WebDriverConfig.initializeDriver();
        homePage = new IceHRMHomePage(driver);
        homePage.openApplication();
    }

    @When("I enter the admin username {string} and password {string}")
    public void i_enter_the_admin_username_and_password(String username, String password) {
        homePage.enterUname(username);
        homePage.enterPassword(password);
    }

    @And("I click the login button for company")
    public void i_click_the_login_button_for_company() {
        homePage.clickLogin();
    }

    @Then("I should be on the company dashboard")
    public void i_should_be_on_the_company_dashboard() {
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://icehrmpro.gamonoid.com/?g=admin&n=dashboard&m=admin_Admin";
        if (!actualUrl.equals(expectedUrl)) {
            throw new AssertionError("URL does not match! Expected: '" + expectedUrl + "', Found: '" + actualUrl + "'");
        }
    }
}
