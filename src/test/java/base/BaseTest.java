package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Initialize the Chrome driver
        driver = new ChromeDriver();

        // Maximize window
        driver.manage().window().maximize();

        // Add an implicit wait globally (optional but helpful)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // ❌ REMOVED: driver.get("https://demoqa.com/text-box");
        // Navigation should happen inside your specific Test classes (e.g. WebTablesTest)
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        // Wait 3 seconds before closing browser to see the test result (keep this if you like watching it run)
        Thread.sleep(3000);

        // Close the browser session completely
        if (driver != null) {
            driver.quit();
        }
    }
}