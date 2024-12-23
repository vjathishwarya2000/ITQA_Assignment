package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WebDriverConfig {

    private static final String CONFIG_FILE = "src/test/resources/config/webdriver.properties";

    public static WebDriver initializeDriver() throws IOException {
        // Load configuration properties
        Properties properties = new Properties();
        properties.load(new FileInputStream(CONFIG_FILE));

        // Set ChromeOptions
        ChromeOptions options = new ChromeOptions();
        boolean headless = Boolean.parseBoolean(properties.getProperty("headless.mode", "false"));
        if (headless) {
            options.addArguments("--headless");
        }

        // Automatically download and manage ChromeDriver using Selenium Manager
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(properties.getProperty("webdriver.base.url"));
        return driver;
    }
}
