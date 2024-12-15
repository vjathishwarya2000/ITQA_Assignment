package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IceHRMHomePage {
    private WebDriver driver;

    public IceHRMHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openApplication() {
        driver.get("https://icehrmpro.gamonoid.com/");
    }

    public void enterUname(String username) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("username")))
                .sendKeys(username);
    }

    public void enterPassword(String password) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("password")))
                .sendKeys(password);
    }

    public void clickLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.tagName("button")))
                .click();
    }
}
