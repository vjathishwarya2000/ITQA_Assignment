package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WebDriverConfig {

    private static final String CONFIG_FILE = "src/test/resources/config/webdriver.properties";

    public static WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(CONFIG_FILE));

        String driverPath = properties.getProperty("drivers.windows.chrome.driverPath"); // Update for OS
        System.setProperty("webdriver.chrome.driver", driverPath);

        boolean headless = Boolean.parseBoolean(properties.getProperty("headless.mode"));
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless");
        }

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(properties.getProperty("webdriver.base.url"));
        return driver;
    }
}
