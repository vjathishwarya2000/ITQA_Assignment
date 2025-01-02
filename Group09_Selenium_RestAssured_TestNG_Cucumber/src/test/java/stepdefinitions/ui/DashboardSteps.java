package stepdefinitions.ui;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.Dashboard;
import runners.TestRunner;

public class DashboardSteps {
    private final WebDriver driver;
    private final Dashboard dashboard;

    // No-argument constructor
    public DashboardSteps() {
        this.driver = TestRunner.getDriver(); // Use the driver from TestRunner
        this.dashboard = new Dashboard(driver);
    }

    @When("I click the 'Manage User' button on the dashboard")
    public void i_click_the_manage_user_button_on_the_dashboard() {
        dashboard.clickManageUser();
    }
}
