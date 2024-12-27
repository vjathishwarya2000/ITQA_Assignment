package stepdefinitions.ui;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import pages.ManageEmployeesLink;
import utils.WebDriverConfig;

import java.io.IOException;

public class ManageEmployeesSteps {

    WebDriver driver;
    ManageEmployeesLink manageEmployeesLink;

    @Given("I am on the IceHRM dashboard")
    public void i_am_on_the_ice_hrm_dashboard() throws IOException {
        driver = WebDriverConfig.initializeDriver(); // Initialize the WebDriver
        driver.get("https://icehrm-open.gamonoid.com"); // Navigate to the IceHRM application
        System.out.println("Navigated to IceHRM Dashboard.");
    }

    @When("I locate the Manage Employees link")
    public void i_locate_the_manage_employees_link() {
        manageEmployeesLink = new ManageEmployeesLink(driver);
        System.out.println("Located the Manage Employees link.");
    }

    @Then("I verify the Manage Employees link is functional")
    public void i_verify_the_manage_employees_link_is_functional() {
        manageEmployeesLink.clickManageEmployees(); // Call the clickManageEmployees method
        System.out.println("Verified the Manage Employees link is functional.");
        driver.quit(); // Close the browser
    }
}
