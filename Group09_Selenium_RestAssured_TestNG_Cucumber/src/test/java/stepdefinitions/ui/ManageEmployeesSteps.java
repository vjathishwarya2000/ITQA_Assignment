
package stepdefinitions.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import io.cucumber.java.en.*;
import pages.ManageEmployeesLink;
import pages.IceHRMHomePage;
import utils.WebDriverConfig;

import java.io.IOException;

public class ManageEmployeesSteps {
    WebDriver driver;
    ManageEmployeesLink manageEmployeesLink;
    IceHRMHomePage homePage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() throws IOException {
        driver = WebDriverConfig.initializeDriver();
        homePage = new IceHRMHomePage(driver);
        homePage.openApplication(); // Open the application using IceHRMHomePage
    }

    @When("I enter my username {string} and password {string}")
    public void i_enter_my_username_and_password(String username, String password) {
        homePage.enterUname(username); // Enter username using IceHRMHomePage
        homePage.enterPassword(password); // Enter password using IceHRMHomePage
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        homePage.clickLogin(); // Click login using IceHRMHomePage
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
    }

    @When("I search for an employee with name {string}")
    public void i_search_for_an_employee_with_name(String employeeName) {
        // Find the search input box using its ID
        WebElement searchBox = driver.findElement(By.cssSelector("input[placeholder='Search by Name']"));

        // Clear any existing text in the search box
        searchBox.clear();

        // Enter the employee name into the search box
        searchBox.sendKeys(employeeName);

        // Verify that "ice" is correctly entered in the search box
        String enteredText = searchBox.getAttribute("value");
        if (!enteredText.equalsIgnoreCase(employeeName)) {
            throw new AssertionError("The search box does not contain the expected text! Expected: '"
                    + employeeName + "', Found: '" + enteredText + "'");
        }

        // Click on the search button using its CSS selector
        WebElement searchButton = driver.findElement(By.cssSelector("button.ant-btn.ant-btn-primary.ant-input-search-button"));
        searchButton.click();
    }

    @Then("I should see the search results containing {string}")
    public void i_should_see_the_search_results_containing(String employeeName) {
        // Wait for search results to load (a more robust solution would use WebDriverWait)
        try {
            Thread.sleep(2000); // Temporary wait for simplicity
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the employee name appears in the results
        WebElement resultItem = driver.findElement(By.cssSelector("li.ant-list-item"));
        WebElement resultTitle = resultItem.findElement(By.cssSelector("h4.ant-list-item-meta-title"));
        WebElement resultDescription = resultItem.findElement(By.cssSelector("div.ant-list-item-meta-description"));

        // Validate the title of the search result
        String actualTitle = resultTitle.getText();
        if (!actualTitle.equalsIgnoreCase(employeeName)) {
            throw new AssertionError("Search result title does not match! Expected: '"
                    + employeeName + "', Found: '" + actualTitle + "'");
        }

        // Validate the description of the search result
        String expectedDescription = "Your Company | Chief Executive Officer";
        String actualDescription = resultDescription.getText();
        if (!actualDescription.equals(expectedDescription)) {
            throw new AssertionError("Search result description does not match! Expected: '"
                    + expectedDescription + "', Found: '" + actualDescription + "'");
        }

        System.out.println("Search successful! Found employee: " + actualTitle + " - " + actualDescription);

        // Close the driver after the test
        driver.quit();
    }
}

