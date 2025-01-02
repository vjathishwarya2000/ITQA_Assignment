package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PermissionFilter {
    private WebDriver driver;

    // Locators
    private By permissionsPageLink = By.id("permissionLink");
    private By filterButton = By.cssSelector("button.btn-primary");
    private By moduleDropdown = By.id("s2id_module_id");
    private By applyFilterButton = By.cssSelector("button.filterBtn");
    private By allModulesButton = By.cssSelector("button.btn-primary");
    private By financeSalaryOption = By.id("select2-result-label-43");

    public PermissionFilter(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToPermissionsPage() {
        driver.findElement(permissionsPageLink).click();
    }

    public void clickFilterButton() {
        driver.findElement(filterButton).click();
    }
    public void selectFinanceSalary() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Open the dropdown
            WebElement dropdownContainer = wait.until(ExpectedConditions.elementToBeClickable(By.id("s2id_module_id")));
            dropdownContainer.click(); // Click the dropdown container
            System.out.println("Dropdown clicked.");

            // Wait for 3 seconds to ensure the dropdown options are loaded
            Thread.sleep(3000);

            // Wait for the "Finance salary" option based on visible text and click it
            WebElement financeSalaryOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='select2-result-label' and text()='Finance salary']")));
            financeSalaryOption.click();
            System.out.println("Finance salary option selected.");
        } catch (TimeoutException e) {
            throw new AssertionError("Unable to locate or select the Finance Salary option. Ensure the dropdown is accessible.", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError("Thread was interrupted during sleep.", e);
        } catch (Exception e) {
            throw new AssertionError("An unexpected error occurred while selecting Finance Salary.", e);
        }
    }




    public void clickApplyFilterButton() {
        driver.findElement(applyFilterButton).click();
    }

    public boolean verifyFilterByModule(String moduleName) {
        // Verify that the filtered list is displayed
        return driver.findElements(By.xpath("//td[text()='" + moduleName + "']")).size() > 0;
    }
}
