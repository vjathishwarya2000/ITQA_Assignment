package stepdefinitions.ui;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import pages.ManageCompanies;
import pages.IceHRMHomePage;
import utils.WebDriverConfig;

import java.io.IOException;

public class ManageCompaniesSteps {
    WebDriver driver;
    ManageCompanies manageCompaniesLink;
    IceHRMHomePage homePage;

    @Given("I am on the login page for the company dashboard")
    public void i_am_on_the_login_page_for_company_dashboard() throws IOException {
        // Initialize WebDriver and open the application
        driver = WebDriverConfig.initializeDriver();
        homePage = new IceHRMHomePage(driver);
        homePage.openApplication();
    }

    @When("I enter the admin username {string} and password {string}")
    public void i_enter_the_admin_username_and_password(String username, String password) {
        // Enter username and password
        homePage.enterUname(username);
        homePage.enterPassword(password);
    }

    @And("I click the login button for company")
    public void i_click_the_login_button_for_company() {
        // Click the login button
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


    @When("I navigate to the company structure page")
    public void i_navigate_to_the_company_structure_page() {
        // Navigate to the company structure page
        manageCompaniesLink = new ManageCompanies(driver);
        manageCompaniesLink.navigateToCompanyStructure();
    }

    @Then("I should see the company structure page with URL {string}")
    public void i_should_see_the_company_structure_page_with_url(String expectedUrl) {
        // Verify that the user is on the company structure page
        String actualUrl = manageCompaniesLink.getCurrentUrl();
        if (!actualUrl.equals(expectedUrl)) {
            throw new AssertionError("URL does not match! Expected: '" + expectedUrl + "', Found: '" + actualUrl + "'");
        }
        System.out.println("Successfully navigated to the Company Structure page: " + actualUrl);
    }

    @When("I click on the Manage Company button")
    public void i_click_on_the_manage_company_button() {
        // Click the Manage Company button
        manageCompaniesLink.clickManageCompanyButton();
        System.out.println("Clicked on the Manage Company button.");
    }
}
