package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ManageEmployeesLink {
    private WebDriver driver;

    public ManageEmployeesLink(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToManageEmployees() {
        String url = "https://icehrmpro.gamonoid.com/?g=admin&n=employees&m=admin_Employees";
        driver.get(url); // Navigate directly to the URL
    }


    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
