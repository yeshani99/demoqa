package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        // Wait 3 seconds before closing browser to see the test result
        Thread.sleep(3000);
        driver.quit();
    }
}