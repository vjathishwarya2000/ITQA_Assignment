package pages;

import org.openqa.selenium.WebDriver;

public class HomePage {

    private IceHRMHomePage iceHRM;

    public HomePage(WebDriver driver) {
        this.iceHRM = new IceHRMHomePage(driver);
    }

    public void openApplication() {
        iceHRM.openApplication();
    }

    public void enterUsername(String username) {
        iceHRM.enterUname(username);
    }

    public void enterPassword(String password) {
        iceHRM.enterPassword(password);
    }

    public void clickLoginButton() {
        iceHRM.clickLogin();
    }
}
