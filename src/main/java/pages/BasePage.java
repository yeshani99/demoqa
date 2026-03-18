package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;  // protected so subclasses can access

    // Initialize driver
    public void initializeDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Navigate to URL
    public void openUrl(String url) {
        driver.get(url);
    }

    // Getter for driver (for tests)
    public WebDriver getDriver() {
        return driver;
    }

    // Close driver
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}