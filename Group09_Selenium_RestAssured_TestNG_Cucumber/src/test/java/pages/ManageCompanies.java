package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManageCompanies {
    private WebDriver driver;

    // Constructor
    public ManageCompanies(WebDriver driver) {
        this.driver = driver;
    }
    public void login(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(By.id("loginButton")).click();
    }

    // Navigate to the company structure page
    public void navigateToCompanyStructure() {
        String url = "https://icehrmpro.gamonoid.com/?g=admin&n=company_structure&m=admin_Company_Structure";
        driver.get(url);
    }

    // Get the current URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Click the Manage Company button
    public void clickManageCompanyButton() {
        driver.findElement(By.id("companyLink")).click();
    }

    // Click the Add New button
    public void clickAddNewButton() {
        driver.findElement(By.xpath("//button[contains(@class, 'ant-btn-primary') and span[text()=' Add New']]")).click();
    }

    // Fill in the company details
    public void fillCompanyDetails(String name, String address, String type, String country) {
        driver.findElement(By.id("name")).sendKeys(name); // Replace "name" with actual ID
        driver.findElement(By.id("address")).sendKeys(address); // Replace "address" with actual ID
        driver.findElement(By.id("type")).sendKeys(type); // Dropdown handling if necessary
        driver.findElement(By.id("country")).sendKeys(country); // Dropdown handling if necessary
    }

    // Click the Save button
    public void clickSaveButton() {
        driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click(); // Adjust XPath if needed
    }

    // Check if a record is present
    public boolean isRecordPresent(String name, String type, String country, String timeZone) {
        try {
            String xpath = String.format(
                    "//tr[td[text()='%s'] and td[text()='%s'] and td[text()='%s'] and td[text()='%s']]",
                    name, type, country, timeZone
            );
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Click the Delete button for a specific record
    public void clickDeleteButton(String name) {
        String xpath = String.format("//tr[td[text()='%s']]//button[contains(@class, 'ant-btn-danger')]", name);
        WebElement deleteButton = driver.findElement(By.xpath(xpath));
        deleteButton.click();

        // Handle confirmation
        driver.findElement(By.xpath("//button[contains(text(),'Yes')]")).click();
    }

    // Check if a record has been deleted
    public boolean isRecordDeleted(String name) {
        try {
            String xpath = String.format("//tr[td[text()='%s']]", name);
            driver.findElement(By.xpath(xpath));
            return false; // If found, the record is not deleted
        } catch (Exception e) {
            return true; // If not found, the record is deleted
        }
    }
}
