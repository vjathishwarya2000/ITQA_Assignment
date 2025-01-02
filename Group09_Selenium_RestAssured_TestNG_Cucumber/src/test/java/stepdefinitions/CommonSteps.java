package stepdefinitions;

import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverConfig;

import java.time.Duration;

public class CommonSteps {
    private WebDriver driver;

    public CommonSteps() {
        this.driver = WebDriverConfig.getDriver();
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
}
