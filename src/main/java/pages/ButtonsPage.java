package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ButtonsPage {

    private WebDriver driver;
    private Actions actions;

    // Locators
    private By doubleClickBtn = By.id("doubleClickBtn");
    private By rightClickBtn = By.id("rightClickBtn");
    private By dynamicClickBtn = By.xpath("//button[text()='Click Me']");

    private By doubleClickMessage = By.id("doubleClickMessage");
    private By rightClickMessage = By.id("rightClickMessage");
    private By dynamicClickMessage = By.id("dynamicClickMessage");

    public ButtonsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void open() {
        driver.get("https://demoqa.com/buttons");
    }

    public void doubleClickButton() {
        WebElement element = driver.findElement(doubleClickBtn);
        actions.doubleClick(element).perform();
    }

    public void rightClickButton() {
        WebElement element = driver.findElement(rightClickBtn);
        actions.contextClick(element).perform();
    }

    public void dynamicClickButton() {
        WebElement element = driver.findElement(dynamicClickBtn);
        element.click();
    }

    public String getDoubleClickMessage() {
        return driver.findElement(doubleClickMessage).getText().trim();
    }

    public String getRightClickMessage() {
        return driver.findElement(rightClickMessage).getText().trim();
    }

    public String getDynamicClickMessage() {
        return driver.findElement(dynamicClickMessage).getText().trim();
    }
}