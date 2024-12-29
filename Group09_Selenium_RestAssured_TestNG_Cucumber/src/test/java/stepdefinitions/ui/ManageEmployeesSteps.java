package stepdefinitions.ui;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import pages.ManageEmployeesLink;
import pages.LoginPage;
import utils.WebDriverConfig;

import java.io.IOException;

public class ManageEmployeesSteps {
    WebDriver driver;
    ManageEmployeesLink manageEmployeesLink;
    LoginPage loginPage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() throws IOException {
        driver = WebDriverConfig.initializeDriver();
        loginPage = new LoginPage(driver);
        driver.get("https://icehrmpro.gamonoid.com/login.php");  // replace with your actual login URL
    }

    @When("I enter my username {string} and password {string}")
    public void i_enter_my_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLoginButton();
    }


    @Then("I should be on the dashboard")
    public void i_should_be_on_the_dashboard() {
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://icehrmpro.gamonoid.com/?g=admin&n=dashboard&m=admin_Admin";
        if (!actualUrl.equals(expectedUrl)) {
            throw new AssertionError("URL does not match! Expected: '" + expectedUrl + "', Found: '" + actualUrl + "'");
        }
    }

    @When("I click on the Manage Employees link")
    public void i_click_on_the_manage_employees_link() {
        manageEmployeesLink = new ManageEmployeesLink(driver);
        manageEmployeesLink.navigateToManageEmployees();
    }

    @Then("I should be navigated to the Manage Employees page with URL {string}")
    public void i_should_be_navigated_to_the_manage_employees_page_with_url(String expectedUrl) throws InterruptedException {
        // Wait for 5 seconds
        Thread.sleep(5000); // Sleep for 5 seconds

        String actualUrl = manageEmployeesLink.getCurrentUrl();
        if (!actualUrl.equals(expectedUrl)) {
            throw new AssertionError("URL does not match! Expected: '" + expectedUrl + "', Found: '" + actualUrl + "'");
        }
        System.out.println("Successfully navigated to the Manage Employees page: " + actualUrl);
        driver.quit();
    }

}
