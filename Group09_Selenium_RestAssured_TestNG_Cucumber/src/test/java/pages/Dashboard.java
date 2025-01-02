package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Dashboard {

    private final WebDriver driver;

    public Dashboard(WebDriver driver) {
        this.driver = driver;
    }

    public void clickManageUser() {
        driver.findElement(By.id("usersLink")).click();
    }
}