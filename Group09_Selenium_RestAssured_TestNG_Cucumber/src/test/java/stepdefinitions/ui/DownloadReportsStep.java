package stepdefinitions.ui;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverConfig;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;

public class DownloadReportsStep {

    private WebDriver driver;
    private WebDriverWait wait;

    public DownloadReportsStep() {
        this.driver = WebDriverConfig.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize wait
    }

    @When("I click on the {string} button")
    public void i_click_on_the_button(String buttonText) {
        try {
            WebElement generateReportButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@id='reportsLink' and contains(text(),'" + buttonText + "')]")
            ));
            generateReportButton.click();
            System.out.println(buttonText + " button clicked.");
        } catch (Exception e) {
            throw new AssertionError("Unable to locate or click the button with text: " + buttonText, e);
        }
    }

    @When("I navigate to the Admin Reports page")
    public void i_navigate_to_the_admin_reports_page() {
        try {
            driver.navigate().to("https://icehrmpro.gamonoid.com/?g=admin&n=reports&m=admin_Reports");
            System.out.println("Navigated to Admin Reports page.");
        } catch (Exception e) {
            throw new AssertionError("Unable to navigate to Admin Reports page.", e);
        }
    }

    @And("I click the download button for {string}")
    public void i_click_the_download_button_for(String reportName) {
        try {
            WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//td[text()='" + reportName + "']/following-sibling::td//img[@class='tableActionButton' and @title='Download']")
            ));
            downloadButton.click();
            System.out.println("Download button clicked for: " + reportName);
        } catch (Exception e) {
            throw new AssertionError("Unable to locate or click the download button for: " + reportName, e);
        }
    }

    @And("I click the final download button")
    public void i_click_the_final_download_button() {
        try {
            WebElement finalDownloadButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@class='btn' and text()='Download']")
            ));
            finalDownloadButton.click();
            System.out.println("Final download button clicked successfully.");
        } catch (Exception e) {
            throw new AssertionError("Unable to locate or click the final download button.", e);
        }
    }

    @And("I click the download report link")
    public void i_click_the_download_report_link() {
        try {
            WebElement downloadLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href,'service.php?file=Report_Active_Employee_Report') and contains(text(),'Download Report')]")
            ));
            downloadLink.click();
            System.out.println("Download Report link clicked successfully.");
        } catch (Exception e) {
            throw new AssertionError("Unable to locate or click the Download Report link.", e);
        }
    }

    @Then("the report should be downloaded successfully")
    public void the_report_should_be_downloaded_successfully() {
        String downloadPath = System.getProperty("user.home") + "/Downloads/";
        String expectedFileName = "Report_Active_Employee_Report.csv"; // Adjust filename if needed
        File downloadedFile = Paths.get(downloadPath, expectedFileName).toFile();

        // Verify file download
        for (int i = 0; i < 10; i++) { // Check for up to 10 seconds
            if (downloadedFile.exists()) {
                System.out.println("Report downloaded successfully: " + downloadedFile.getAbsolutePath());
                return;
            }
            try {
                Thread.sleep(1000); // Wait 1 second before rechecking
            } catch (InterruptedException ignored) {
            }
        }
        throw new AssertionError("The report file was not downloaded: " + expectedFileName);
    }
}
