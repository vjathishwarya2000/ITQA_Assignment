package stepdefinitions.ui;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import runners.TestRunner;

public class UIApplicationLogin {

    private final WebDriver driver;

    public UIApplicationLogin() {
        this.driver = TestRunner.getDriver();
    }

    @Given("I open the IceHRM application")
    public void i_open_the_ice_hrm_application() {
        driver.get("https://icehrmpro.gamonoid.com/");
    }

    @When("I enter {string} as username")
    public void i_enter_as_username(String username) {
        driver.findElement(By.id("username")).sendKeys(username);
    }

    @When("I enter {string} as password")
    public void i_enter_as_password(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        driver.findElement(By.cssSelector(".btn-info.btn-medium")).click();
    }

    @Then("I should see the dashboard with title {string}")
    public void i_should_see_the_dashboard_with_title(String title) {
        String actualTitle = driver.getTitle();
        if (!actualTitle.equals(title)) {
            throw new AssertionError("Expected title: " + title + ", but found: " + actualTitle);
        }
        System.out.println("Dashboard title verified successfully: " + actualTitle);
    }
}