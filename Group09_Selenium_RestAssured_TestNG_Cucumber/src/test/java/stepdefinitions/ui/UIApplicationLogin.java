package stepdefinitions.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import pages.Dashboard;
import pages.HomePage;

public class UIApplicationLogin {

    WebDriver driver;
    HomePage homePage;
    Dashboard dashboard;

    @Given("I open the IceHRM application")
    public void i_open_the_ice_hrm_application() {
        driver = new ChromeDriver(); // Replace with WebDriver setup
        homePage = new HomePage(driver);
        homePage.openApplication();
    }

    @When("I enter {string} as username")
    public void i_enter_as_username(String username) {
        homePage.enterUsername(username);
    }

    @When("I enter {string} as password")
    public void i_enter_as_password(String password) {
        homePage.enterPassword(password);
    }

    @And("I click the login button")
    public void i_click_the_login_button() {
        homePage.clickLoginButton();
    }

    @Then("I should see the dashboard with title {string}")
    public void i_should_see_the_dashboard_with_title(String expectedTitle) {
        dashboard = new Dashboard(driver);
        dashboard.verifyAdmin();
        driver.quit();
    }
}
