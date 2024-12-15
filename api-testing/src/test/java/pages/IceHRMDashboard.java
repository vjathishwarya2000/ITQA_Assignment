package pages;

import org.openqa.selenium.WebDriver;

public class IceHRMDashboard {
    private WebDriver driver;

    public IceHRMDashboard(WebDriver driver) {
        this.driver = driver;
    }

    public String getDashboardTitle() {
        return driver.getTitle();
    }
}
