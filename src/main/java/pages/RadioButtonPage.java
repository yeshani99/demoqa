package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RadioButtonPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By yesRadio = By.id("yesRadio");
    private By impressiveRadio = By.id("impressiveRadio");
    private By noRadio = By.id("noRadio");
    private By resultText = By.cssSelector(".text-success");

    // Constructor
    public RadioButtonPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Click Yes Radio
    public void clickYes() {
        wait.until(ExpectedConditions.elementToBeClickable(yesRadio)).click();
    }

    // Click Impressive Radio
    public void clickImpressive() {
        wait.until(ExpectedConditions.elementToBeClickable(impressiveRadio)).click();
    }

    // Click No Radio
    public void clickNo() {
        wait.until(ExpectedConditions.elementToBeClickable(noRadio)).click();
    }

    // Get Result Text
    public String getResultText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(resultText)).getText();
    }
}