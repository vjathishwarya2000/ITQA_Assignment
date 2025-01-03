package pages;

import org.openqa.selenium.By;
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
    // Update existing company details
    public boolean updateCompanyDetails(String currentName, String updatedName, String updatedDetails, String updatedAddress, String updatedType, String updatedCountry, String updatedTimeZone) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Locate the row containing the record to be updated
            String recordXpath = String.format("//tr[td[text()='%s']]", currentName);
            WebElement recordRow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(recordXpath)));

            // Click the Edit button in the corresponding row
            WebElement editButton = recordRow.findElement(By.xpath(".//button[contains(@class, 'edit-button-class')]")); // Adjust XPath for your application
            editButton.click();

            // Wait for the update form to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

            // Update the form fields
            WebElement nameField = driver.findElement(By.id("name"));
            nameField.clear();
            nameField.sendKeys(updatedName);

            WebElement detailsField = driver.findElement(By.id("description"));
            detailsField.clear();
            detailsField.sendKeys(updatedDetails);

            WebElement addressField = driver.findElement(By.id("address"));
            addressField.clear();
            addressField.sendKeys(updatedAddress);

            // Handle the Type dropdown
            WebElement typeDropdown = driver.findElement(By.id("rc_select_0"));
            typeDropdown.click();
            WebElement typeOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'ant-select-item-option') and text()='" + updatedType + "']")));
            typeOption.click();

            // Handle the Country dropdown
            WebElement countryDropdown = driver.findElement(By.id("rc_select_1"));
            countryDropdown.click();
            WebElement countryOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'ant-select-item-option') and text()='" + updatedCountry + "']")));
            countryOption.click();

            // Handle the TimeZone dropdown
            WebElement timeZoneDropdown = driver.findElement(By.id("rc_select_2"));
            timeZoneDropdown.click();
            WebElement timeZoneOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'ant-select-item-option') and text()='" + updatedTimeZone + "']")));
            timeZoneOption.click();

            // Click the Save button
            WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
            saveButton.click();

            // Validate the record is updated
            String updatedRecordXpath = String.format(
                    "//tr[td[text()='%s'] and td[text()='%s'] and td[text()='%s'] and td[text()='%s'] and td[text()='%s']]",
                    updatedName, updatedAddress, updatedType, updatedCountry, updatedTimeZone);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(updatedRecordXpath)));

            return true; // Update successful
        } catch (Exception e) {
            System.out.println("Error updating record: " + e.getMessage());
            return false; // Update failed
        }
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
    public boolean createRecord(String name, String type, String country, String timeZone) {
        try {
            // Locate and click the "Add New" button using XPath
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement addNewButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[span[contains(text(),'Add New')]]")
            ));
            addNewButton.click();

            // Wait for the form to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

            // Fill in the form fields
            driver.findElement(By.id("name")).sendKeys(name);
            driver.findElement(By.id("type")).sendKeys(type);
            driver.findElement(By.id("country")).sendKeys(country);
            driver.findElement(By.id("timeZone")).sendKeys(timeZone);

            // Submit the form
            driver.findElement(By.xpath("//button[span[contains(text(),'Save')]]")).click();

            // Wait for the record to appear in the table
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//tr[td[text()='" + name + "'] and td[text()='" + type + "'] and td[text()='" + country + "'] and td[text()='" + timeZone + "']]")
            ));

            return true; // Record created successfully
        } catch (Exception e) {
            System.out.println("Error creating record: " + e.getMessage());
            return false; // Record creation failed
        }
    }

    public void fillCompanyDetails(String name, String details, String address, String type, String country) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Fill in the Name field
        driver.findElement(By.id("title")).sendKeys(name);

        // Fill in the Details field
        driver.findElement(By.id("description")).sendKeys(details);

        // Fill in the Address field
        driver.findElement(By.id("address")).sendKeys(address);

        // Handle the Type dropdown
        WebElement typeDropdown = driver.findElement(By.id("rc_select_0"));
        typeDropdown.click();
        WebElement typeOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'ant-select-item-option') and text()='" + type + "']")));
        typeOption.click();
        try {
            Thread.sleep(5000); // Add a 5-second delay
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Handle the Country dropdown
// Handle the Country dropdown
        WebElement countryDropdown = driver.findElement(By.id("rc_select_1")); // Locate the search input
        countryDropdown.click(); // Click to expand the dropdown

// Type the desired country in the search box
        countryDropdown.sendKeys("Sri Lanka");

// Wait for the desired option to appear in the dropdown
        WebElement countryOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'ant-select-item-option') and text()='Sri Lanka']")));

// Select the country
        countryOption.click();

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
        // Locate the row containing the record with the specified name
        String xpath = String.format("//tr[td[text()='%s']]//span[contains(@class, 'ant-tag-volcano') and contains(text(),'Delete')]", name);
        WebElement deleteButton = driver.findElement(By.xpath(xpath));

        // Click the Delete button
        deleteButton.click();

        // Handle the confirmation dialog
        WebElement confirmButton = driver.findElement(By.xpath("//button[contains(text(),'Yes')]"));
        confirmButton.click();
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
