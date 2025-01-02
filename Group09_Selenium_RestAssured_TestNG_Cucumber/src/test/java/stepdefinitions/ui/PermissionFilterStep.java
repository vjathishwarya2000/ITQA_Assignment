package stepdefinitions.ui;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PermissionFilter;
import utils.WebDriverConfig;

import java.time.Duration;

public class PermissionFilterStep {
    private WebDriver driver;
    private PermissionFilter permissionFilterPage;

    public PermissionFilterStep() {
        this.driver = WebDriverConfig.getDriver();
        this.permissionFilterPage = new PermissionFilter(driver);
    }
    @When("I navigate to the permissions page")
    public void i_navigate_to_the_permissions_page() {
        // Navigate to the permissions page
        driver.get("https://icehrmpro.gamonoid.com/?g=admin&n=permissions&m=admin_Manage_Permissions");
    }

    @Given("I am on the permissions page")
    public void i_am_on_the_permissions_page() {
        permissionFilterPage.navigateToPermissionsPage();
    }

    @When("I click the filter button")
    public void i_click_the_filter_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increase wait time to 15 seconds

        try {
            // Ensure the element is present and visible
            WebElement filterButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.btn.btn-small.btn-primary")));

            // Click the filter button
            filterButton.click();
            System.out.println("Filter button clicked successfully.");
        } catch (TimeoutException e) {
            throw new AssertionError("Unable to locate or click the filter button. Ensure the button is visible and clickable.", e);
        } catch (Exception e) {
            throw new AssertionError("An unexpected error occurred while clicking the filter button.", e);
        }
    }



    @And("I select {string} from the All Modules dropdown")
    public void i_select_from_the_all_modules_dropdown(String module) {
        if (module.equalsIgnoreCase("Finance salary")) {
            permissionFilterPage.selectFinanceSalary();
        } else {
            throw new IllegalArgumentException("Unsupported module: " + module);
        }
    }

    @And("I click the apply filter button")
    public void i_click_the_apply_filter_button() {
        permissionFilterPage.clickApplyFilterButton();
    }

    @Then("the permissions list should be filtered by {string}")
    public void the_permissions_list_should_be_filtered_by(String module) {
        String pageSource = driver.getPageSource();
        if (!pageSource.contains(module)) {
            throw new AssertionError("Filtered results do not contain the expected module: " + module);
        }
    }
}
