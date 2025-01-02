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

    @When("I navigate to the company structure page")
    public void i_navigate_to_the_company_structure_page() {
        manageCompaniesLink = new ManageCompanies(driver);
        manageCompaniesLink.navigateToCompanyStructure();
    }

    @When("I click the Add New button")
    public void i_click_the_add_new_button() {
        manageCompaniesLink.clickAddNewButton();
    }

    @And("I fill in the new company details with Name {string}, Details {string}, Address {string}, Type {string}, and Country {string}")
    public void i_fill_in_the_new_company_details(String name, String details, String address, String type, String country) {
        manageCompaniesLink.fillCompanyDetails(name, details, address, type, country);
    }

    @And("I save the new company")
    public void i_save_the_new_company() {
        manageCompaniesLink.clickSaveButton();
    }

    @Then("I should see the new company with Name {string}, Type {string}, and Country {string} in the list")
    public void i_should_see_the_new_company(String name, String type, String country) {
        boolean isPresent = manageCompaniesLink.isRecordPresent(name, type, country, "");
        if (!isPresent) {
            throw new AssertionError("New company not found in the list.");
        }
    }

    @When("I update the company details from Name {string} to new Name {string}, Details {string}, Address {string}, Type {string}, Country {string}, and TimeZone {string}")
    public void i_update_the_company_details(String currentName, String updatedName, String updatedDetails, String updatedAddress, String updatedType, String updatedCountry, String updatedTimeZone) {
        boolean isUpdated = manageCompaniesLink.updateCompanyDetails(currentName, updatedName, updatedDetails, updatedAddress, updatedType, updatedCountry, updatedTimeZone);
        if (!isUpdated) {
            throw new AssertionError("Failed to update the company details.");
        }
    }

    @Then("I should see the updated company with Name {string}, Type {string}, and Country {string} in the list")
    public void i_should_see_the_updated_company(String updatedName, String updatedType, String updatedCountry) {
        boolean isPresent = manageCompaniesLink.isRecordPresent(updatedName, updatedType, updatedCountry, "");
        if (!isPresent) {
            throw new AssertionError("Updated company not found in the list.");
        }
    }
}
