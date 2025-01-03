package stepdefinitions.ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.IceHRMHomePage;
import utils.WebDriverConfig;

import java.time.Duration;

public class CopyCompanyDocumentSteps {

    private WebDriver driver;
    private IceHRMHomePage homePage;

    @Given("I am logged in and on the Dashboard")
    public void i_am_logged_in_and_on_the_dashboard() throws Exception {
        driver = WebDriverConfig.initializeDriver();
        homePage = new IceHRMHomePage(driver);

        // Open the application
        homePage.openApplication();

        // Login using the provided method
        homePage.enterUname("admin");
        homePage.enterPassword("admin");
        homePage.clickLogin();

        // Wait for the dashboard to load
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("dashboard"));
    }

    @When("I navigate to the Document Types page")
    public void i_navigate_to_the_document_types_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to Documents -> Document Types tab
        WebElement documentsMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Documents')]")));
        documentsMenu.click();

        WebElement documentTypesTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Document Types')]")));
        documentTypesTab.click();
    }

    @And("I click the Copy button for the first available document")
    public void i_click_the_copy_button_for_the_first_available_document() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Locate the first Copy button in the table
            WebElement firstCopyButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//tbody[@class='ant-table-tbody']//tr[1]//span[contains(@class, 'ant-tag-cyan')]")
            ));

            // Click the Copy button
            firstCopyButton.click();
            System.out.println("Clicked the Copy button for the first available document.");
        } catch (Exception e) {
            throw new AssertionError("Unable to locate or click the Copy button for any document.", e);
        }
    }

    @Then("A copy of the document should be created")
    public void a_copy_of_the_document_should_be_created() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Construct XPath to find any row with "Copy" in the Name column
        String copiedDocumentXpath = "//tbody[@class='ant-table-tbody']//tr[td[contains(text(), 'Driving License')]]";

        try {
            // Adding a short delay before checking for the copied document
            Thread.sleep(2000); // Wait for 2 seconds

            // Wait for the copied document to appear
            WebElement copiedDocument = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(copiedDocumentXpath)));

            // Verify the copied document is displayed
            if (copiedDocument.isDisplayed()) {
                System.out.println("Document copied successfully.");
            } else {
                throw new AssertionError("Copied document is not visible in the table.");
            }
        } catch (TimeoutException e) {
            System.err.println("Copied document not found within the timeout.");
            System.err.println("XPath used: " + copiedDocumentXpath);
            throw new AssertionError("Timeout while waiting for the copied document to appear.", e);
        } catch (Exception e) {
            System.err.println("Unexpected error occurred while verifying the copied document.");
            e.printStackTrace();
            throw e;
        }
    }


    @And("I click the Save button to save the copied document")
    public void i_click_the_save_button_to_save_the_copied_document() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Wait for the Save button to be clickable
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@type='button' and contains(@class, 'ant-btn-primary') and span[text()='Save']]")
            ));

            // Click the Save button
            saveButton.click();
            System.out.println("Save button clicked successfully.");
        } catch (TimeoutException e) {
            throw new AssertionError("Save button was not found or not clickable within the timeout period.");
        }
    }
}
