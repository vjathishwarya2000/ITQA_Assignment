
package pages;

import org.openqa.selenium.WebDriver;

public class ManageEmployeesPage {
    private WebDriver driver;

    public ManageEmployeesPage(WebDriver driver) {
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
