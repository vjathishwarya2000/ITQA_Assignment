package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter username
    public void enterUsername(String username) {
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);
    }

    // Method to enter password
    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
    }

    // Method to click the login button
    public void clickLoginButton() {
        // Locate the login button using its class attribute or any other attribute
        WebElement loginButton = driver.findElement(By.xpath("//button[@class='btn btn-info btn-medium w-100 text-uppercase' and @type='button']"));
        loginButton.click();
    }
}
