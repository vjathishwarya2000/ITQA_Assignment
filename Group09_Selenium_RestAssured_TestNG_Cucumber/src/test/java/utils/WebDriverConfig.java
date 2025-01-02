package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WebDriverConfig {

    private static final String CONFIG_FILE = "src/test/resources/config/webdriver.properties";
    private static WebDriver driver;

    /**
     * Initializes the WebDriver instance if not already initialized.
     * @return WebDriver instance.
     * @throws IOException if the configuration file cannot be read.
     */
    public static WebDriver initializeDriver() throws IOException {
        if (driver == null) {
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
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get(properties.getProperty("webdriver.base.url"));
        }
        return driver;
    }

    /**
     * Returns the current WebDriver instance.
     * @return WebDriver instance.
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver not initialized. Call initializeDriver() first.");
        }
        return driver;
    }

    /**
     * Quits the WebDriver and sets it to null.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
