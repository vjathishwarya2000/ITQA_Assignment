package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManageEmployeesLink {

    private WebDriver driver;

    public ManageEmployeesLink(WebDriver driver) {
        this.driver = driver;
    }

    public void clickManageEmployees() {
        WebElement manageEmployeesLink = driver.findElement(By.id("employeeLink"));

        if (!manageEmployeesLink.isDisplayed() || !manageEmployeesLink.isEnabled()) {
            throw new AssertionError("Manage Employees link is not functional!");
        }

        String href = manageEmployeesLink.getAttribute("href");
        if (!href.contains("employees")) { // Check if it contains the expected substring
            throw new AssertionError("The href attribute for Manage Employees link is incorrect! Found: " + href);
        }

        manageEmployeesLink.click();
        System.out.println("Manage Employees link clicked successfully!");


        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.equals(href)) {
            throw new AssertionError("Navigation to Manage Employees page failed! Expected URL: " + href + ", Found: " + currentUrl);
        }
        System.out.println("Navigated to Manage Employees page successfully: " + currentUrl);
    }
}
