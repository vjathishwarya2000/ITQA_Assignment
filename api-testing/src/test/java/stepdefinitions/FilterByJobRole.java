package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Dashboard;
import pages.EmployeesPage;
import utils.WebDriverConfig;

import java.io.IOException;
import java.time.Duration;

public class FilterByJobRole {

    WebDriver driver;
    EmployeesPage employeesPage;

    @Given("I am logged into IceHRM")
    public void i_am_logged_into_icehrm() throws IOException {
        // Initialize WebDriver
        driver = WebDriverConfig.initializeDriver();

        // Login to IceHRM
        Dashboard dashboard = new Dashboard(driver);
        dashboard.verifyAdmin(); // Verifies login success
    }

    @When("I navigate to the Employees page")
    public void i_navigate_to_the_employees_page() {
        employeesPage = new EmployeesPage(driver);
        employeesPage.navigateToEmployeesPage();
    }

    @Then("I should see the employee details table")
    public void i_should_see_the_employee_details_table() {
        employeesPage.verifyEmployeeDetailsTableVisible();
    }
}
