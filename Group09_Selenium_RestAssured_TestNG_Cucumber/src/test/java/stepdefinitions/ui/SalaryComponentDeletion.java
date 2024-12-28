package stepdefinitions.ui;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import utils.WebDriverConfig;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SalaryComponentDeletion {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

    public SalaryComponentDeletion() throws IOException {
        driver = WebDriverConfig.initializeDriver(); // Initialize WebDriver
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        homePage = new HomePage(driver);
    }

    @Given("I am logged in with valid credentials")
    public void i_am_logged_in_with_valid_credentials() {
        homePage.openApplication();
        homePage.enterUsername("admin");
        homePage.enterPassword("admin");
        homePage.clickLoginButton();

        // Wait for the dashboard link to be visible
        By dashboardLocator = By.cssSelector("a[href*='dashboard&m=module_Personal_Information']");
        wait.until(ExpectedConditions.presenceOfElementLocated(dashboardLocator));
    }

    @Given("I am on the Salary Component page")
    public void i_am_on_the_salary_component_page() {
        // Navigate to the Salary Component page
        driver.get("https://icehrm-open.gamonoid.com/?g=modules&n=salary&m=module_Finance");

        // Wait for the Salary tab to be visible and active
        WebElement salaryTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tabEmployeeSalary")));
        if (!salaryTab.getAttribute("class").contains("active")) {
            salaryTab.click();
        }

        // Wait for the Salary Component table to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tabPageEmployeeSalary")));
    }

    @When("I delete the salary component {string}")
    public void i_delete_the_salary_component(String componentName) {
        // Locate and click the delete button for the specific salary component
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[text()='" + componentName + "']/following-sibling::td//img[contains(@src, 'delete.png')]")
        ));
        deleteButton.click();

        // Wait for the confirmation modal to appear
        WebElement modalDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteModel")));

        // Verify the modal title
        WebElement modalTitle = modalDialog.findElement(By.id("deleteModelLabel"));
        if (!modalTitle.getText().equals("Confirm Deletion")) {
            throw new AssertionError("Modal title does not match! Expected: 'Confirm Deletion', Found: '" + modalTitle.getText() + "'");
        }

        // Click the "Delete" button to confirm
        WebElement confirmButton = modalDialog.findElement(By.xpath("//div[@class='modal-footer']//button[contains(text(), 'Delete')]"));
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();

        System.out.println("Salary component '" + componentName + "' deleted successfully.");
    }

    @Then("The confirmation alert for deleting the salary component {string} should be displayed")
    public void the_confirmation_alert_should_be_displayed(String componentName) {
        // Wait for the modal dialog to appear
        WebElement modalDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteModel")));

        // Verify the alert title
        WebElement alertTitle = modalDialog.findElement(By.id("deleteModelLabel"));
        if (!alertTitle.getText().equals("Confirm Deletion")) {
            throw new AssertionError("Alert title does not match! Expected: 'Confirm Deletion', Found: '" + alertTitle.getText() + "'");
        }

        // Verify the alert message
        WebElement alertMessage = modalDialog.findElement(By.id("deleteModelBody"));
        if (!alertMessage.getText().contains("Are you sure you want to delete this item")) {
            throw new AssertionError("Alert message does not match! Expected message to contain: 'Are you sure you want to delete this item', Found: '" + alertMessage.getText() + "'");
        }

        //System.out.println("Alert displayed successfully with the correct title and message.");
    }

}
