package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeesPage {

    private WebDriver driver;
    private By employeesMenu = By.xpath("//a[@href='?g=admin&n=employees&m=admin_Employees']");
    private By employeeTable = By.xpath("//div[@class='row employeeListRow']"); // Adjust the XPath as needed

    public EmployeesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToEmployeesPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Click on Employees menu
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(employeesMenu));
        menu.click();

        // Wait for the Employees page to load
        wait.until(ExpectedConditions.urlContains("admin&n=employees&m=admin_Employees"));
        System.out.println("Navigated to the Employees page.");
    }

    public void verifyEmployeeDetailsTableVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Verify if employee table is visible
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeTable));
        if (!table.isDisplayed()) {
            throw new AssertionError("Employee details table is not visible.");
        }

        System.out.println("Employee details table is visible.");
    }
}
