package pages;

import org.openqa.selenium.WebDriver;

public class Dashboard {

    private IceHRMDashboard iceHRMDashboard;

    public Dashboard(WebDriver driver) {
        this.iceHRMDashboard = new IceHRMDashboard(driver);
    }

    public void verifyTitle() {
        String dashboardTitle = iceHRMDashboard.getDashboardTitle();
        if (!dashboardTitle.equals("IceHrm")) {
            throw new AssertionError("Dashboard title does not match! Expected: 'IceHrm', Found: '" + dashboardTitle + "'");
        }
        System.out.println("Dashboard title verified successfully: " + dashboardTitle);
    }

}
