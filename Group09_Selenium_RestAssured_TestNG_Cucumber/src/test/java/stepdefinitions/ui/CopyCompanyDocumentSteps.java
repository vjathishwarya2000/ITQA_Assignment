package stepdefinitions.ui;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.IceHRMHomePage;
import utils.WebDriverConfig;

import java.io.IOException;
import java.time.Duration;

public class CopyCompanyDocumentSteps {
    private WebDriver driver;
    private IceHRMHomePage homePage;

    @Before
    public void setUp() throws Exception {
        driver = WebDriverConfig.initializeDriver();
        homePage = new IceHRMHomePage(driver);
    }

    @After
    public void tearDown() {
        WebDriverConfig.quitDriver();
    }


    @Given("I am logged in and on the Dashboard")
    public void i_am_logged_in_and_on_the_dashboard() throws Exception {
        homePage.openApplication();
        homePage.enterUname("admin");
        homePage.enterPassword("admin");
        homePage.clickLogin();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("dashboard"));
    }

    @When("I navigate to the Document Types page")
    public void i_navigate_to_the_document_types_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement documentsMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Documents')]")));
        documentsMenu.click();

        WebElement documentTypesTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Document Types')]")));
        documentTypesTab.click();
    }

    @And("I click the Copy button for the first available document")
    public void i_click_the_copy_button_for_the_first_available_document() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement firstCopyButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//tbody[@class='ant-table-tbody']//tr[1]//span[contains(@class, 'ant-tag-cyan')]")));
        firstCopyButton.click();
    }

    @Then("A copy of the document should be created")
    public void a_copy_of_the_document_should_be_created() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String copiedDocumentXpath = "//tbody[@class='ant-table-tbody']//tr[td[contains(text(), 'Copy')]]";

        try {
            WebElement copiedDocument = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(copiedDocumentXpath)));
            assert copiedDocument.isDisplayed() : "Copied document not visible in the table.";
        } catch (TimeoutException e) {
            System.err.println("Page source:\n" + driver.getPageSource()); // Debugging aid
            throw new AssertionError("Timeout while waiting for the copied document to appear. Verify the XPath or DOM changes.", e);
        }
    }


    @And("I click the Save button to save the copied document")
    public void i_click_the_save_button_to_save_the_copied_document() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='button' and contains(@class, 'ant-btn-primary') and span[text()='Save']]")));
        saveButton.click();
    }
}
