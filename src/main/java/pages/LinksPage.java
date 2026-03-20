package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Set;

public class LinksPage {

    WebDriver driver;

    // Locators for Links page
    By homeLink = By.id("simpleLink");
    By dynamicLink = By.id("dynamicLink");
    By createdLink = By.id("created");
    By noContentLink = By.id("no_content");
    By movedLink = By.id("moved");
    By badRequestLink = By.id("bad_request");
    By unauthorizedLink = By.id("unauthorized");
    By forbiddenLink = By.id("forbidden");
    By notFoundLink = By.id("not_found");
    By responseText = By.id("linkResponse");

    public LinksPage(WebDriver driver) {
        this.driver = driver;
    }

    // Get all links on the page
    public List<WebElement> getAllLinks() {
        return driver.findElements(By.tagName("a"));
    }

    // Click on simple link (Home)
    public void clickSimpleLink() {
        driver.findElement(homeLink).click();
    }

    // Click on dynamic link
    public void clickDynamicLink() {
        driver.findElement(dynamicLink).click();
    }

    // Click on Created link (201 status)
    public void clickCreatedLink() {
        driver.findElement(createdLink).click();
    }

    // Click on No Content link (204 status)
    public void clickNoContentLink() {
        driver.findElement(noContentLink).click();
    }

    // Click on Moved link (301 status)
    public void clickMovedLink() {
        driver.findElement(movedLink).click();
    }

    // Click on Bad Request link (400 status)
    public void clickBadRequestLink() {
        driver.findElement(badRequestLink).click();
    }

    // Click on Unauthorized link (401 status)
    public void clickUnauthorizedLink() {
        driver.findElement(unauthorizedLink).click();
    }

    // Click on Forbidden link (403 status)
    public void clickForbiddenLink() {
        driver.findElement(forbiddenLink).click();
    }

    // Click on Not Found link (404 status)
    public void clickNotFoundLink() {
        driver.findElement(notFoundLink).click();
    }

    // Get response text after clicking a link
    public String getResponseText() {
        return driver.findElement(responseText).getText();
    }

    // Check if a link is displayed
    public boolean isSimpleLinkDisplayed() {
        return driver.findElement(homeLink).isDisplayed();
    }

    // Check if a link is enabled
    public boolean isSimpleLinkEnabled() {
        return driver.findElement(homeLink).isEnabled();
    }

    // Get href attribute of a link
    public String getLinkHref(By locator) {
        return driver.findElement(locator).getAttribute("href");
    }

    // Get text of a link
    public String getLinkText(By locator) {
        return driver.findElement(locator).getText();
    }

    // Get current window count
    public int getWindowCount() {
        return driver.getWindowHandles().size();
    }

    // Switch to new window
    public void switchToNewWindow() {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
        }
    }

    // Get current URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Check if response text contains specific message
    public boolean responseContains(String message) {
        try {
            String response = getResponseText();
            return response.contains(message);
        } catch (Exception e) {
            return false;
        }
    }

    // Wait for response to appear (basic wait)
    public void waitForResponse() throws InterruptedException {
        Thread.sleep(2000);
    }
}
