package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ManageCompanies {
    private WebDriver driver;

    // Constructor
    public ManageCompanies(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCompanyStructure() {
        driver.get("https://icehrmpro.gamonoid.com/?g=admin&n=company_structure&m=admin_Company_Structure");
    }

    public void clickAddNewButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addNewButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class, 'ant-btn-primary') and span[text()=' Add New']]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addNewButton);
        addNewButton.click();
    }

    public void fillCompanyDetails(String name, String details, String address, String type, String country) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("title")).sendKeys(name);
        driver.findElement(By.id("description")).sendKeys(details);
        driver.findElement(By.id("address")).sendKeys(address);

        // Handle Type Dropdown
        selectDropdownOption(wait, "rc_select_0", type);

        // Handle Country Dropdown
        selectDropdownOption(wait, "rc_select_1", country);
    }

    public void clickSaveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Save')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
        saveButton.click();
    }

    public boolean isRecordPresent(String name, String type, String country, String timeZone) {
        try {
            String xpath = String.format(
                    "//tr[td[text()='%s'] and td[text()='%s'] and td[text()='%s'] and td[text()='%s']]",
                    name, type, country, timeZone
            );
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (Exception e) {
            System.err.println("Record not found: " + e.getMessage());
            return false;
        }
    }

    private void selectDropdownOption(WebDriverWait wait, String dropdownId, String optionText) {
        try {
            WebElement dropdown = driver.findElement(By.id(dropdownId));
            dropdown.click();
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'ant-select-item-option') and text()='" + optionText + "']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
            option.click();
        } catch (Exception e) {
            throw new AssertionError("Failed to select option '" + optionText + "' in dropdown with ID: " + dropdownId, e);
        }
    }
}